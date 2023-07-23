package io.github.aquerr.lem.web.rest;

import io.github.aquerr.lem.application.config.security.JwtService;
import io.github.aquerr.lem.application.security.AuthenticatedUser;
import io.github.aquerr.lem.application.security.AuthenticationFacade;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthRestController
{
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginRequest loginRequest)
    {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authentication.getPrincipal();
        String jwt = jwtService.createJwt(authenticatedUser);

        return ResponseEntity.ok(new JwtResponse(jwt, authenticatedUser.getUsername(), authenticatedUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList()));
    }

    @GetMapping("/myself")
    public ResponseEntity<UserProfile> getCurrentUser()
    {
        AuthenticatedUser authenticatedUser = authenticationFacade.getCurrentUser();
        if (authenticatedUser == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(UserProfile.of(authenticationFacade.getCurrentUser()));
    }

    @PostMapping("/invalidate")
    public ResponseEntity<?> invalidate(HttpServletRequest httpServletRequest)
    {
        String jwt = jwtService.getJwtToken(httpServletRequest);
        jwtService.invalidate(jwt);
        return ResponseEntity.ok().build();
    }

    public record LoginRequest(String username, String password) { }

    public record JwtResponse(String jwt, String username, List<String> authorities) {}

    public record UserProfile(String username, List<String> authorities)
    {
        public static UserProfile of(AuthenticatedUser authenticatedUser)
        {
            return new UserProfile(authenticatedUser.getUsername(), authenticatedUser.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList());
        }
    }
}
