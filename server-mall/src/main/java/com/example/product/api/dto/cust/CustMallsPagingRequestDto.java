package com.example.product.api.dto.cust;

import com.example.product.api.dto.RequestListDto;
import lombok.Data;

@Data
public class CustMallsPagingRequestDto extends RequestListDto {
    private Long custId;
}
