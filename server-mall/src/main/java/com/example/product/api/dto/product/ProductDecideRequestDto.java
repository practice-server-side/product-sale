package com.example.product.api.dto.product;

import com.example.product.enums.DecideProductType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class ProductDecideRequestDto {
    @Min(1)
    private Long mallId;
    @NotEmpty
    private List<Long> applyProductHitoryIds;
    private DecideProductType decideProductType;
}
