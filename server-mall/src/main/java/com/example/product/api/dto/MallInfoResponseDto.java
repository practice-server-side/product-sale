package com.example.product.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MallInfoResponseDto {
    private Long mallId;
    private String mallKey;
    private String clientKey;
}