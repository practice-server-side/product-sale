package com.example.product.api.controller;

import com.example.product.exception.NotFoundException;
import com.example.product.api.dto.CustRegisterRequestDto;
import com.example.product.api.dto.CustInfoResponseDto;
import com.example.product.api.dto.CustRegisterResponseDto;
import com.example.product.api.model.Cust;
import com.example.product.api.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server-mall/v1/cust")
public class CustController {
    private final CustRepository custRepository;
    private final MessageSourceAccessor messageSource;

    /**
     *  고객 정보 조회
     */
    @GetMapping("/{custId}")
    public ResponseEntity<?> custInfo(@PathVariable("custId") Long custId) { //TODO : 권한처리하기

        Cust cust = custRepository.findById(custId)
                .orElseThrow(() -> new NotFoundException("C01", messageSource.getMessage("C01")));

        return ResponseEntity.ok(
                CustInfoResponseDto.builder()
                        .userName(cust.getUserName())
                        .uesrPhone(cust.getUserPhone())
                        .build()
        );
    }

    /**
     *  고객 회원 가입
     */
    @PostMapping
    public ResponseEntity<?> custRegister(@RequestBody CustRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust newCust = Cust.builder()
                .userName(request.getUserName()) //TODO : 복호화 가능한 암호화
                .userPhone(request.getUserPhone()) //TODO : 복호화 가능한 암호화
                .clientKey(UUID.randomUUID().toString())
                .build();

        custRepository.save(newCust);

        return ResponseEntity.created(selfLink).body(
                CustRegisterResponseDto.builder()
                        .custId(newCust.getCustId())
                        .build()
        );
    }

}
