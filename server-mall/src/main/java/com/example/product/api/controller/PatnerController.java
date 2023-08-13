package com.example.product.api.controller;


import com.example.product.annotation.Cust;
import com.example.product.api.dto.partner.ApplyPartnerPagingRequestDto;
import com.example.product.api.dto.partner.ApplyPartnerPagingResponseDto;
import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.api.model.specification.ApplyPartnerSpecification;
import com.example.product.api.repository.ApplyPartnerHistoryRepository;
import com.example.product.dto.CurrentCust;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-mall/v1/partner")
public class PatnerController {
    private final ApplyPartnerHistoryRepository applyPartnerHistoryRepository;

    /**
     * 몰 파트너 신청 리스트 조회
     * @param currentCust
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getApplyPartners(
            @Cust CurrentCust currentCust,
            @Valid ApplyPartnerPagingRequestDto request) {

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize(), Sort.Direction.DESC, request.getSort());
        Specification<ApplyPartnerHistory> specification = ApplyPartnerSpecification.getApplyPartnerSpecification(currentCust, request);
        Page<ApplyPartnerHistory> dataList = applyPartnerHistoryRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ApplyPartnerPagingResponseDto.builder()
                        .totalCount(dataList.getTotalElements())
                        .page(dataList.getNumber())
                        .pageSize(dataList.getSize())
                        .partners(
                                dataList.stream()
                                        .map(data -> ApplyPartnerPagingResponseDto.Partners.builder()
                                                .applyPartnerHistoryId(data.getApplyPartnerHistoryId())
                                                .partnerName(data.getPartnerName())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build()

        );
    }

}
