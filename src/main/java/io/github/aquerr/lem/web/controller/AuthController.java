package io.github.aquerr.lem.web.controller;

import io.github.aquerr.lem.application.config.security.JwtService;
import io.github.aquerr.lem.application.security.AuthenticatedUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.createJwt(loginRequest.username());

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt, authenticatedUser.getUsername(), authenticatedUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()));
    }

    public record LoginRequest(String username, String password) { }

    public record JwtResponse(String jwt, String username, List<String> authorities) {}
}
