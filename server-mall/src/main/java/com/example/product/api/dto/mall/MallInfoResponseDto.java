package com.example.product.api.dto.mall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class MallInfoResponseDto {
    private Long mallId;
    private String mallName;
    private String mallKey;
    private String custKey;
}
