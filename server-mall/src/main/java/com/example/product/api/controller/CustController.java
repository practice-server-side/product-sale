package com.example.product.api.controller;

import com.example.product.exception.NotFoundException;
import com.example.product.api.dto.CustInfoResponseDto;
import com.example.product.api.model.Cust;
import com.example.product.api.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-mall/v1/cust")
public class CustController {
    private final CustRepository custRepository;
    private final MessageSourceAccessor messageSource;

    //TODO : 위치 옮기기
    /**
     *  고객 정보 조회
     */
    @GetMapping("/{custId}")
    public ResponseEntity<?> custInfo(@PathVariable("custId") Long custId) {

        Cust cust = custRepository.findById(custId)
                .orElseThrow(() -> new NotFoundException("C01", messageSource.getMessage("C01")));

        return ResponseEntity.ok(
                CustInfoResponseDto.builder()
                        .userName(cust.getUserName())
                        .uesrPhone(cust.getUserPhone())
                        .build()
        );
    }


}
