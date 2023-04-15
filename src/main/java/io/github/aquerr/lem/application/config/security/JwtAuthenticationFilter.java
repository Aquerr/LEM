package io.github.aquerr.lem.application.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Log4j2
@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtService jwtService;
    private final LemUserDetailsService lemUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwt = getJwtToken(request);
        if (jwt == null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        Jws<Claims> claimsJws = jwtService.validateJwt(jwt);
        if (claimsJws != null)
        {
            authenticate(claimsJws, request);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticate(Jws<Claims> claimsJws, HttpServletRequest request)
    {
        String username = claimsJws.getBody().getSubject();
        UserDetails userDetails = lemUserDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    private String getJwtToken(HttpServletRequest httpServletRequest)
    {
        return Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION_HEADER))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.substring(BEARER.length()))
                .orElse(null);
    }
}
