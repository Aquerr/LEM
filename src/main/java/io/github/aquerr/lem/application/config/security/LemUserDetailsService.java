package io.github.aquerr.lem.application.config.security;

import io.github.aquerr.lem.application.security.AuthenticatedUser;
import io.github.aquerr.lem.domain.model.LemUser;
import io.github.aquerr.lem.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@AllArgsConstructor
public class LemUserDetailsService implements UserDetailsService
{
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LemUser lemUser = userRepository.findByUsername(username);
        if (lemUser == null)
        {
            throw new UsernameNotFoundException("User does not exist!");
        }
        return new AuthenticatedUser(lemUser.id(), lemUser.username(), lemUser.password(), getClientIp(httpServletRequest), lemUser.authorities().stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
    }

    private String getClientIp(HttpServletRequest request)
    {
        String remoteAddr = "";

        if (request != null)
        {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr))
            {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
