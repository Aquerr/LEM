package io.github.aquerr.lem.application.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;

@Service
@Log4j2
public class JwtService
{
    private static final Key KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Value("${lem.security.jwt.issuer}")
    private String jwtIssuer;
    @Value("${lem.security.jwt.expiration-time}")
    private Duration jwtExpirationTime;

    public String createJwt(String username)
    {
        return Jwts.builder()
                .setSubject(username)
                .signWith(KEY)
                .setIssuer(jwtIssuer)
                .setExpiration(Date.from(Instant.now().plus(jwtExpirationTime)))
                .setIssuedAt(Date.from(Instant.now()))
                .compact();
    }

    public Jws<Claims> validateJwt(String jwt)
    {
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
}
