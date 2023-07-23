package io.github.aquerr.lem.domain.log.exception;

import io.github.aquerr.lem.domain.ApiException;
import org.springframework.http.HttpStatus;

@ApiException(status = HttpStatus.INTERNAL_SERVER_ERROR, message = "Could not read the log file.")
public class CouldNotReadLogException extends RuntimeException
{

    public CouldNotReadLogException(String message)
    {
        super(message);
    }
}
