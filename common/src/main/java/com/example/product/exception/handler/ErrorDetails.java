package com.example.product.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int status;
    private String errorCode;
    private String errorMessage;
    private String path;
    private String timeStamp;

}
