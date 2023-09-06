package com.example.product.api.dto;

import lombok.Data;

@Data
public class MallMemberLoginRequestDto {
    private String memberId;
    private String loginPassword;
}
