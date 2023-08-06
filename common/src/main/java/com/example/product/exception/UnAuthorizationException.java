package com.example.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizationException extends RuntimeException {
    private final String errorCode;

    public UnAuthorizationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UnAuthorizationException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public UnAuthorizationException(UnAuthorizationException e) {
        super(e.getMessage());
        this.errorCode = e.getErrorCode();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
