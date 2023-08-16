package com.example.product.api.dto.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductRegisterRequestDto {

    @Min(1)
    private Long mallId;
    @NotNull
    private List<Product> registerPriductList;

    @Min(1)
    private Long partnerId;

    @Data
    public static class Product {
        @NotBlank
        private String productName;
        @Min(1)
        private Integer productPrice;
    }
}
