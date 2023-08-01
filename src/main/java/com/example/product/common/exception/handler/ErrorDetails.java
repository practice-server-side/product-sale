package com.example.product.common.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int status;
    private String errorCode;
    private String errorMessage;
    private String path;
    private LocalDateTime timeStamp;

//    public ErrorDetails(int status, String errorCode, String errorMessage, String path, LocalDateTime timeStamp) {
//        super();
//        this.status = status;
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//        this.path = path;
//        this.timeStamp = timeStamp;
//    }
}
