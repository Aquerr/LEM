package io.github.aquerr.lem.domain;

import org.springframework.http.HttpStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ApiException
{
    HttpStatus status();

    String message();
}
