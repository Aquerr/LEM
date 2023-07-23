package io.github.aquerr.lem.application.config.security;

import io.github.aquerr.lem.application.security.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class JwtService
{
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //TODO: We should store them in DB also for jwtExpirationTime time and load them when application restarts.
    private static final Map<String, BlackListedJwt> BLACK_LISTED_JWTS = new HashMap<>();
    private static ScheduledExecutorService BLACK_LISTED_JWTS_CLEARER_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private static Future<?> BLACK_LISTED_JWTS_CLEARER_FUTURE = BLACK_LISTED_JWTS_CLEARER_EXECUTOR_SERVICE
            .scheduleAtFixedRate(JwtService::clearBlacklistedJwts, 1, 10, TimeUnit.SECONDS);

    private static void clearBlacklistedJwts()
    {
        for (final BlackListedJwt blackListedJwt : List.copyOf(BLACK_LISTED_JWTS.values()))
        {
            if (LocalDateTime.now().isAfter(blackListedJwt.getExpirationTime()))
            {
                BLACK_LISTED_JWTS.remove(blackListedJwt.getJwt());
            }
        }
    }

    @Value("${lem.security.jwt.issuer}")
    private String jwtIssuer;
    @Value("${lem.security.jwt.expiration-time}")
    private Duration jwtExpirationTime;

    public String createJwt(AuthenticatedUser authenticatedUser)
    {
        Claims claims = Jwts.claims()
                .setSubject(authenticatedUser.getUsername())
                .setIssuer(jwtIssuer)
                .setExpiration(Date.from(Instant.now().plus(jwtExpirationTime)))
                .setIssuedAt(Date.from(Instant.now()));
        claims.put("authorities", authenticatedUser.getAuthorities());

        return Jwts.builder()
                .setSubject(authenticatedUser.getUsername())
                .signWith(KEY)
                .setClaims(claims)
                .compact();
    }

    public Jws<Claims> validateJwt(String jwt)
    {
        if (BLACK_LISTED_JWTS.containsKey(jwt))
            throw new IllegalStateException();

        try
        {
            return Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .requireIssuer(jwtIssuer)
                    .build()
                    .parseClaimsJws(jwt);
        }
        catch (Exception exception)
        {
            log.error(exception::getMessage);
            throw exception;
        }
    }

    public String getJwtToken(HttpServletRequest httpServletRequest)
    {
        return Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION_HEADER))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.substring(BEARER.length()))
                .orElse(null);
    }

    public void invalidate(String jwt)
    {
        Jws<Claims> jws = validateJwt(jwt);
        Date exprationDate = jws.getBody().getExpiration();
        BLACK_LISTED_JWTS.put(jwt, BlackListedJwt.of(jwt, exprationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
    }

    @lombok.Value(staticConstructor = "of")
    private static class BlackListedJwt
    {
        String jwt;
        LocalDateTime expirationTime;
    }
}
