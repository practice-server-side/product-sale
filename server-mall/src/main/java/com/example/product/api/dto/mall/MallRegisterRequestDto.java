package com.example.product.api.dto.mall;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MallRegisterRequestDto {
    @NotBlank
    private String mallName;
}
