package com.example.product.api.dto.product;

import com.example.product.api.dto.ResponseListDto;
import com.example.product.enums.DecidePartnerType;
import com.example.product.enums.DecideProductType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyProductPagingResponseDto extends ResponseListDto {
    private List<ApplyProduct> applyProductList;
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyProduct {
        private Long applyProductHistoryId;
        private String productName;
        private LocalDateTime registerDate;
        private DecideProductType decideProductType;
        private String imageUrl1;
        private String imageUrl2;
        private Integer productPrice;
    }
}
