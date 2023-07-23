package io.github.aquerr.lem.domain;

import lombok.Value;

@Value(staticConstructor = "of")
public class RestErrorResponse
{
    int status;
    String message;
}
