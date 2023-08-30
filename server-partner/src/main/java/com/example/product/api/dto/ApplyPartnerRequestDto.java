package com.example.product.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplyPartnerRequestDto {
    @Min(1)
    private Long mallId;

    @NotBlank
    private String partnerName;

    @NotBlank
    private String partnerPhone;

    private String partnerRepresentative;
}
