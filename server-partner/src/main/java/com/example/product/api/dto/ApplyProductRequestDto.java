package com.example.product.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyProductRequestDto {
    @NotBlank
    private String productName;
    @Min(10)
    private Integer productPrice;
    @NotBlank
    private String imageUrl1;
    private String imageUrl2;
    private Long partnerId;
}
