package com.example.product.api.controller;

import com.example.product.api.dto.MallInfoResponseDto;
import com.example.product.api.dto.MallRegisterRequestDto;
import com.example.product.api.dto.MallRegisterResponseDto;
import com.example.product.api.model.Cust;
import com.example.product.api.model.Mall;
import com.example.product.api.repository.CustRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.common.exception.NotFoundException;
import com.example.product.common.exception.UnAuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("project/v1/mall")
public class MallController {
    private final MallRepository mallRepository;
    private final CustRepository custRepository;
    private final MessageSourceAccessor messageSource;

    /**
     *  고객 몰 리스트 조회
     */
    @GetMapping
    public ResponseEntity<?> custMalls() {

        return ResponseEntity.ok("");
    }

    /**
     *  몰 상세 조회
     */
    @GetMapping("/{mallId}")
    public ResponseEntity<?> mallInfo(
            @RequestParam("custId") Long custId,
            @PathVariable("mallId") Long mallId) {

        Mall mall = mallRepository.findById(mallId)
                .orElseThrow(() -> new NotFoundException("M01", messageSource.getMessage("M01")));

        if (!mall.getCust().getCustId().equals(custId)) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        return ResponseEntity.ok(
                MallInfoResponseDto.builder()
                        .mallId(mall.getMallId())
                        .mallKey(mall.getMallKey())
                        .clientKey(mall.getCust().getClientKey())
                        .build()
        );
    }

    /**
     *  몰 등록
     */
    @PostMapping
    public ResponseEntity<?> mallRegister(
            @RequestParam("custId") Long custId,
            @RequestBody MallRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust cust = custRepository.findById(custId)
                .orElseThrow(() -> new NotFoundException("C01", messageSource.getMessage("C01")));

        Mall mall = Mall.builder()
                .mallName(request.getMallName())
                .mallKey(UUID.randomUUID().toString())
                .cust(cust)
                .build();

        mallRepository.save(mall);

        return ResponseEntity.created(selfLink).body(
                MallRegisterResponseDto.builder()
                        .mallId(mall.getMallId())
                        .build()
        );
    }

}
