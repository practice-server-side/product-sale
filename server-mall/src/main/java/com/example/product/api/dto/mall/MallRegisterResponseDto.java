package com.example.product.api.dto.mall;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MallRegisterResponseDto {
    private Long mallId;
}
