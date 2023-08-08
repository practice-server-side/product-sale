package com.example.product.api.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String loginId;
    private String loginPassword;
}
