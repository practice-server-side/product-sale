package com.example.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    private final String errorCode;

    public BadRequestException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public BadRequestException(BadRequestException e) {
        super(e.getMessage());
        this.errorCode = e.getErrorCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
