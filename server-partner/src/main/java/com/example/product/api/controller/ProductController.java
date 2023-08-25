package com.example.product.api.controller;

import com.example.product.api.dto.ApplyProductRequestDto;
import com.example.product.api.model.ApplyProductHistory;
import com.example.product.api.model.Partner;
import com.example.product.api.repository.ApplyProductHistoryRepository;
import com.example.product.api.repository.PartnerRepository;
import com.example.product.api.repository.ProductRepository;
import com.example.product.enums.DecideProductType;
import com.example.product.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-partner/v1/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final PartnerRepository partnerRepository;
    private final ApplyProductHistoryRepository applyProductHistoryRepository;

    /**
     *  파트너사 상품 등록 신청
     */
    @PostMapping
    public ResponseEntity<?> applyProduct(
            @RequestBody ApplyProductRequestDto request) {
        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Partner partner = partnerRepository.findById(request.getPartnerId())
                .orElseThrow(() -> new NotFoundException("", ""));

        ApplyProductHistory newData = ApplyProductHistory.builder()
                .productName(request.getProductName())
                .productPrice(request.getProductPrice())
                .imageUrl1(request.getImageUrl1())
                .imageUrl2(request.getImageUrl2())
                .decideProductType(DecideProductType.WAIT)
                .partner(partner)
                .build();

        applyProductHistoryRepository.save(newData);

        return ResponseEntity.created(selfLink).build();
    }

}
