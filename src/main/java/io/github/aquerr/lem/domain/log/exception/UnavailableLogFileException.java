package io.github.aquerr.lem.domain.log.exception;

import io.github.aquerr.lem.domain.ApiException;
import org.springframework.http.HttpStatus;

@ApiException(status = HttpStatus.SERVICE_UNAVAILABLE, message = "Unavailable log file.")
public class UnavailableLogFileException extends RuntimeException
{

    public UnavailableLogFileException(String message)
    {
        super(message);
    }
}
