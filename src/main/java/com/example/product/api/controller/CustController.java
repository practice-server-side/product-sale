package com.example.product.api.controller;

import com.example.product.api.dto.RequestCustJoinDto;
import com.example.product.api.dto.ResponseCustJoinDto;
import com.example.product.api.model.Cust;
import com.example.product.api.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("project/v1/")
public class CustController {
    private final CustRepository custRepository;

    @PostMapping("/cust")
    public ResponseEntity<?> custJoin(@RequestBody RequestCustJoinDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust newCust = Cust.builder()
                .userName(request.getUserName()) //TODO : 암호화
                .userPhone(request.getUserPhone()) //TODO : 암호화
                .build();

        custRepository.save(newCust);

        return ResponseEntity.created(selfLink).body(
                ResponseCustJoinDto.builder()
                        .custId(newCust.getCustId())
                        .build()
        );
    }

}
