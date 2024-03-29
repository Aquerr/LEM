package io.github.aquerr.lem.application.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class WebSecurityConfig
{
    @EnableWebSecurity
    @EnableMethodSecurity
    @ConditionalOnProperty(value = "lem.security.enabled", havingValue = "true")
    public static class EnabledSecurityConfiguration
    {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                               LemUserDetailsService lemUserDetailsService,
                                               JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception
        {
            httpSecurity.authorizeHttpRequests()
                    .requestMatchers("/api/v1/auth/**").permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers("/public/**").authenticated()
                    .requestMatchers("/static/**").authenticated()
                    .requestMatchers("/*").permitAll()
                    .anyRequest().authenticated()
            .and()
            .userDetailsService(lemUserDetailsService)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin().disable()
            .httpBasic().disable()
            .cors()
            .and()
            .csrf().disable()
            .headers().frameOptions().disable();
            return httpSecurity.build();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
        {
            return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource()
        {
            CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name()));
            corsConfiguration.setAllowedOrigins(List.of("*"));

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return source;
        }
    }

    @EnableWebSecurity
    @ConditionalOnProperty(value = "lem.security.enabled", matchIfMissing = true, havingValue = "false")
    public static class DisabledSecurityConfiguration
    {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
        {
            httpSecurity.cors()
                    .and()
                    .headers().frameOptions().disable()
                    .and()
                    .authorizeHttpRequests(authorizeHttpRequests -> {
                        authorizeHttpRequests.requestMatchers("/**").permitAll();
                    })
                    .csrf().disable()
                    .httpBasic();
            return httpSecurity.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }
    }
}
