package com.example.product.api.dto;

import lombok.Data;

@Data
public class CustRegisterRequestDto {
    private String loginId;
    private String loginPassword;
    private String userName;
    private String userPhone;
}
