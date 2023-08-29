package com.example.product.api.dto.product;

import com.example.product.api.dto.RequestListDto;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplyProductPagingRequestDto extends RequestListDto {
    @Min(1)
    private Long partnerId;
}
