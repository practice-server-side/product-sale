package com.example.product.api.dto.partner;

import com.example.product.api.dto.ResponseListDto;
import com.example.product.enums.DecidePartnerType;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyPartnerPagingResponseDto extends ResponseListDto {

    private List<ApplyPartner> applyPartnerList;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ApplyPartner {
        private Long applyPartnerHistoryId;
        private String partnerName;
        private LocalDateTime registerDate;
        private DecidePartnerType decidePartnerType;
        private String decideReason;
    }
}
