package com.example.product.api.controller;

import com.example.product.annotation.Cust;
import com.example.product.api.dto.product.ApplyProductPagingRequestDto;
import com.example.product.api.dto.partner.ApplyPartnerPagingResponseDto;
import com.example.product.api.dto.product.ApplyProductPagingResponseDto;
import com.example.product.api.dto.product.ProductDecideRequestDto;
import com.example.product.api.model.*;
import com.example.product.api.model.specification.ApplyProductSpecification;
import com.example.product.api.repository.ApplyProductHistoryRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.api.repository.PartnerRepository;
import com.example.product.api.repository.ProductRepository;
import com.example.product.dto.CurrentCust;
import com.example.product.enums.DecideProductType;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-mall/v1/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final MallRepository mallRepository;
    private final PartnerRepository partnerRepository;
    private final MessageSourceAccessor messageSource;
    private final ApplyProductHistoryRepository applyProductHistoryRepository;

    /**
     * 파트너 상품 신청 리스트 조회
     *
     * @param currentCust
     * @param request
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getApplyProducts(
            @Cust CurrentCust currentCust,
            @Valid ApplyProductPagingRequestDto request) {

        Partner currentCustHavePartner = partnerRepository.findById(request.getPartnerId())
                .orElseThrow(() -> new NotFoundException("P02", messageSource.getMessage("P02")));

        if (!currentCustHavePartner.getMall().getCust().getCustId().equals(currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize(), Sort.Direction.DESC, request.getSort());
        Specification<ApplyProductHistory> specification = ApplyProductSpecification.getApplyProductSpecification(currentCust, request);
        Page<ApplyProductHistory> dataList = applyProductHistoryRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                ApplyProductPagingResponseDto.builder()
                        .totalCount(dataList.getTotalElements())
                        .page(dataList.getNumber())
                        .pageSize(dataList.getSize())
                        .applyProductList(
                                dataList.stream()
                                        .map(data -> ApplyProductPagingResponseDto.ApplyProduct.builder()
                                                .applyProductHistoryId(data.getApplyProductHistoryId())
                                                .productName(data.getProductName())
                                                .registerDate(data.getRegisterDate())
                                                .decideProductType(data.getDecideProductType())
                                                .imageUrl1(data.getImageUrl1())
                                                .imageUrl2(data.getImageUrl2())
                                                .productPrice(data.getProductPrice())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build()
        );
    }


    /**
     * 파트너사 상품 결정
     */
    @PostMapping
    public ResponseEntity<?> decideProduct(
            @RequestBody @Valid ProductDecideRequestDto request,
            @Cust CurrentCust currentCust) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Mall mall = mallRepository.findById(request.getMallId())
                .orElseThrow(() -> new NotFoundException("M01", messageSource.getMessage("M01")));


        if (!Objects.equals(mall.getCust().getCustId(), currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        List<ApplyProductHistory> applyProductHistoryList = applyProductHistoryRepository.findByApplyProductHistoryIdInAndDecideProductType(request.getApplyProductHitoryIds(), DecideProductType.WAIT);

        if (applyProductHistoryList.stream().anyMatch(applyProductHistory -> !mall.getPartnerList().contains(applyProductHistory.getPartner()))) {
            throw new UnAuthorizationException("M03", messageSource.getMessage("M03"));
        }

        if (applyProductHistoryList.size() == 0) {
            throw new NotFoundException("M04", messageSource.getMessage("M04"));
        }

        if (request.getDecideProductType().equals(DecideProductType.ACCEPT)) {
            List<Product> newDataList = applyProductHistoryList.stream()
                    .map(applyProductHistory -> Product.builder()
                            .productName(applyProductHistory.getProductName())
                            .productPrice(applyProductHistory.getProductPrice())
                            .imageUrl1(applyProductHistory.getImageUrl1())
                            .imageUrl2(applyProductHistory.getImageUrl2())
                            .partner(applyProductHistory.getPartner())
                            .build())
                    .collect(Collectors.toList());

            productRepository.saveAll(newDataList);

        }

        applyProductHistoryList.forEach(
                applyProductHistory -> applyProductHistory.setDecideProductType(request.getDecideProductType())
        );

        applyProductHistoryRepository.saveAll(applyProductHistoryList);

        return ResponseEntity.created(selfLink).build();
    }
}
