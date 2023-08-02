package com.example.product.api.controller;

import com.example.product.api.dto.MallRegisterRequestDto;
import com.example.product.api.dto.MallRegisterResponseDto;
import com.example.product.api.model.Cust;
import com.example.product.api.model.Mall;
import com.example.product.api.repository.CustRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.common.exception.NotFoundException;
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
                        .mallKey(mall.getMallKey())
                        .clientKey(cust.getClientKey())
                        .build()
        );
    }

}
