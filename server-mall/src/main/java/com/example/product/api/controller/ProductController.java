package com.example.product.api.controller;

import com.example.product.annotation.Cust;
import com.example.product.api.dto.product.ProductRegisterRequestDto;
import com.example.product.api.model.Mall;
import com.example.product.api.model.Partner;
import com.example.product.api.model.Product;
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
    private final PartnerRepository partnerRepository;

    /**
     * 파트너사 상품 등록
     */
    @PostMapping
    public ResponseEntity<?> registerProduct(
            @RequestBody @Valid ProductRegisterRequestDto request,
            @Cust CurrentCust currentCust) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Mall mall = mallRepository.findById(request.getMallId())
                .orElseThrow(() -> new NotFoundException("", ""));

        if (!Objects.equals(mall.getCust().getCustId(), currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        Partner partner = partnerRepository.findById(request.getPartnerId())
                .orElseThrow(() -> new NotFoundException("", messageSource.getMessage("")));

        if (!Objects.equals(partner.getMall().getMallId(), mall.getMallId())) {
            throw new UnAuthorizationException("", messageSource.getMessage(""));
        }

        List<Product> newDataList = request.getRegisterPriductList().stream()
                .map(requestProduct -> Product.builder()
                        .productName(requestProduct.getProductName())
                        .productPrice(requestProduct.getProductPrice())
                        .partner(partner)
                        .build())
                .collect(Collectors.toList());

        productRepository.saveAll(newDataList);

        return ResponseEntity.created(selfLink).build();
    }
}
