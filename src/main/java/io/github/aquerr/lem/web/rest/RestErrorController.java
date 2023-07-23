package io.github.aquerr.lem.web.rest;

import io.github.aquerr.lem.domain.ApiException;
import io.github.aquerr.lem.domain.RestErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestErrorController
{
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<RestErrorResponse> handleException(RuntimeException exception)
    {
        if (exception.getClass().isAnnotationPresent(ApiException.class))
        {
            ApiException apiException = exception.getClass().getAnnotation(ApiException.class);
            return ResponseEntity.status(apiException.status()).body(RestErrorResponse.of(apiException.status().value(), apiException.message()));
        }
        return ResponseEntity.internalServerError().body(RestErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()));
    }
}
