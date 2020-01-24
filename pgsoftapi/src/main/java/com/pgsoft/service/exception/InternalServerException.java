package com.pgsoft.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {
    public InternalServerException(String s) {
        super(s);
    }

    public InternalServerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InternalServerException(Throwable throwable) {
        super(throwable);
    }
}
