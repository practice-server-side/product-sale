package com.example.product.api.dto.partner;

import com.example.product.enums.DecidePartnerType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DecidePartnerRequestDto {
    @NotNull
    private DecidePartnerType decidePartnerType;
    private String decideReason;
}
