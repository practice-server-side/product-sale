package com.example.common.exception.handler;

import com.example.common.exception.BadRequestException;
import com.example.common.exception.NotFoundException;
import com.example.common.exception.UnAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    @ExceptionHandler({ NotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorDetails> handleExceptionInternal(
            NotFoundException ex, HttpServletRequest request) {

        return new ResponseEntity<>(
                ErrorDetails.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .errorCode(ex.getErrorCode())
                        .errorMessage(ex.getMessage())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now().toString())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ BadRequestException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDetails> handleExceptionInternal(
            BadRequestException ex, HttpServletRequest request) {

        return new ResponseEntity<>(
                ErrorDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .errorCode(ex.getErrorCode())
                        .errorMessage(ex.getMessage())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now().toString())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ UnAuthorizationException.class })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ErrorDetails> handleExceptionInternal(
            UnAuthorizationException ex, HttpServletRequest request) {

        return new ResponseEntity<>(
                ErrorDetails.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .errorCode(ex.getErrorCode())
                        .errorMessage(ex.getMessage())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now().toString())
                        .build(),
                HttpStatus.UNAUTHORIZED);
    }
}
