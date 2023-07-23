package io.github.aquerr.lem.application.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.aquerr.lem.domain.ApiException;
import io.github.aquerr.lem.domain.RestErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor
@Component
@Order(1)
public class ApiExceptionHandlerFilter extends OncePerRequestFilter
{
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        try
        {
            filterChain.doFilter(request, response);
        }
        catch (Exception exception)
        {
            if (exception.getClass().isAnnotationPresent(ApiException.class))
            {
                ApiException apiException = exception.getClass().getAnnotation(ApiException.class);

                RestErrorResponse restErrorResponse = RestErrorResponse.of(apiException.status().value(), apiException.message());
                response.setStatus(restErrorResponse.getStatus());
                response.getWriter().write(objectMapper.writeValueAsString(restErrorResponse));
            }
            else
            {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(exception.getMessage());
            }
        }
    }
}
