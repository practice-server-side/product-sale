package com.example.product.api.controller;


import com.example.product.annotation.Cust;
import com.example.product.api.dto.partner.ApplyPartnerPagingRequestDto;
import com.example.product.api.dto.partner.ApplyPartnerPagingResponseDto;
import com.example.product.api.dto.partner.DecidePartnerRequestDto;
import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.api.model.Mall;
import com.example.product.api.model.Partner;
import com.example.product.api.model.specification.ApplyPartnerSpecification;
import com.example.product.api.repository.ApplyPartnerHistoryRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.api.repository.PartnerRepository;
import com.example.product.dto.CurrentCust;
import com.example.product.enums.DecidePartnerType;
import com.example.product.exception.NotFoundException;
import com.example.product.exception.UnAuthorizationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-mall/v1/partner")
public class PatnerController {
    private final ApplyPartnerHistoryRepository applyPartnerHistoryRepository;
    private final MallRepository mallRepository;
    private final MessageSourceAccessor messageSource;
    private final PartnerRepository partnerRepository;

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

        Mall currentCustHaveMall = mallRepository.findById(request.getMallId())
                .orElseThrow(() -> new NotFoundException("M01", messageSource.getMessage("M01")));

        if (!currentCustHaveMall.getCust().getCustId().equals(currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize(), Sort.Direction.DESC, request.getSort());
        Specification<ApplyPartnerHistory> specification = ApplyPartnerSpecification.getApplyPartnerSpecification(currentCust, request);
        Page<ApplyPartnerHistory> dataList = applyPartnerHistoryRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ApplyPartnerPagingResponseDto.builder()
                        .totalCount(dataList.getTotalElements())
                        .page(dataList.getNumber())
                        .pageSize(dataList.getSize())
                        .applyPartnerList(
                                dataList.stream()
                                        .map(data -> ApplyPartnerPagingResponseDto.ApplyPartner.builder()
                                                .applyPartnerHistoryId(data.getApplyPartnerHistoryId())
                                                .partnerName(data.getPartnerName())
                                                .decidePartnerType(data.getDecidePartnerType())
                                                .decideReason(data.getDecideReason())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build()

        );
    }

    /**
     * 파트너 결정하기
     * @param applyPartnerHistoryId
     * @param currentCust
     * @return
     */
    @PostMapping("/{applyPartnerHistoryId}")
    public ResponseEntity<?> decidePartner(
            @PathVariable("applyPartnerHistoryId") Long applyPartnerHistoryId,
            @RequestBody @Valid DecidePartnerRequestDto request,
            @Cust CurrentCust currentCust) {

        ApplyPartnerHistory applyPartnerHistory = applyPartnerHistoryRepository.findByApplyPartnerHistoryIdAndDecidePartnerType(applyPartnerHistoryId, DecidePartnerType.WAIT)
                .orElseThrow(() -> new NotFoundException("P01", messageSource.getMessage("P01")));

        Mall mall = mallRepository.findById(applyPartnerHistory.getMallId())
                .orElseThrow(() -> new NotFoundException("M01", messageSource.getMessage("M01")));

        if (!Objects.equals(mall.getCust().getCustId(), currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }


        if (request.getDecidePartnerType().equals(DecidePartnerType.ACCEPT)) {

            Partner newData = com.example.product.api.model.Partner.builder()
                    .partnerName(applyPartnerHistory.getPartnerName())
                    .partnerPhone(applyPartnerHistory.getPartnerPhone())
                    .partnerRepresentative(applyPartnerHistory.getPartnerRepresentative())
                    .mall(mall)
                    .build();

            partnerRepository.save(newData);

        }

        applyPartnerHistory.setDecidePartnerType(request.getDecidePartnerType());
        applyPartnerHistory.setDecideReason(request.getDecideReason());
        applyPartnerHistoryRepository.save(applyPartnerHistory);


        return ResponseEntity.noContent().build();
    }


}
