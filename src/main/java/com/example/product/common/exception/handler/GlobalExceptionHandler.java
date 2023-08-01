package com.example.product.common.exception.handler;

import com.example.product.common.exception.NotFoundException;
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
                        .timeStamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

}
