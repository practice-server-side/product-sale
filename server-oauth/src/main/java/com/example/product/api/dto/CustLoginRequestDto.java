package com.example.product.api.dto;

import lombok.Data;

@Data
public class CustLoginRequestDto {
    private String loginId;
    private String loginPassword;
}
