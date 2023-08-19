package com.example.product.api.controller;

import com.example.product.annotation.Cust;
import com.example.product.api.dto.product.ProductDecideRequestDto;
import com.example.product.api.model.ApplyProductHistory;
import com.example.product.api.model.Mall;
import com.example.product.api.model.Partner;
import com.example.product.api.model.Product;
import com.example.product.api.repository.ApplyProductHistoryRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.api.repository.PartnerRepository;
import com.example.product.api.repository.ProductRepository;
import com.example.product.dto.CurrentCust;
import com.example.product.exception.NotFoundException;
import com.example.product.exception.UnAuthorizationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
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
    private final MessageSourceAccessor messageSource;
    private final ApplyProductHistoryRepository applyProductHistoryRepository;

    /**
     * 파트너사 상품 등록
     */
    @PostMapping
    public ResponseEntity<?> decideProduct(
            @RequestBody @Valid ProductDecideRequestDto request,
            @Cust CurrentCust currentCust) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Mall mall = mallRepository.findById(request.getMallId())
                .orElseThrow(() -> new NotFoundException("", ""));

        if (!Objects.equals(mall.getCust().getCustId(), currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        List<ApplyProductHistory> applyProductHistoryList = applyProductHistoryRepository.findByApplyProductHistoryIdIn(request.getApplyProductHitoryIds());

        if(applyProductHistoryList.stream().anyMatch(applyProductHistory -> !mall.getPartnerList().contains(applyProductHistory.getPartner()))){
            throw new UnAuthorizationException("", ""); //요청 값의 등록요청번호는 당신의 몰 파트너사의 등록요청이 아닙니다.
        }

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

        return ResponseEntity.created(selfLink).build();
    }
}
