package com.example.product.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@Data
public class CustMallsPagingResponseDto extends ResponseListDto {
    private List<Malls> malls;

    @Data
    @Builder
    @AllArgsConstructor
    public static class Malls {
        private Long mallId;
        private String mallName;
        private String mallKey;
        //TODO : LocalDateTime To String annotation 만들기
        private LocalDateTime registerDate;
    }
}
