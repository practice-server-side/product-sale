package com.example.product.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String errorCode;

    public NotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public String getErrorCode() {
        return errorCode;
    }
}
