package com.example.product.api.controller;

import com.example.product.api.dto.ApplyPartnerRequestDto;
import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.api.repository.ApplyPartnerHistoryRepository;
import com.example.product.api.repository.MallRepository;
import com.example.product.enums.DecidePartnerType;
import com.example.product.exception.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-partner/v1/patner")
public class PartnerController {
    private final MallRepository mallRepository;
    private final ApplyPartnerHistoryRepository applyPartnerHistoryRepository;
    private final MessageSourceAccessor messageSource;


    /**
     * 파트너 신청하기
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<?> applyPartner(@RequestBody @Valid ApplyPartnerRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        if (!mallRepository.existsById(request.getMallId())) {
            throw  new NotFoundException("M01", messageSource.getMessage("M01"));
        }

        ApplyPartnerHistory newData = ApplyPartnerHistory.builder()
                .mallId(request.getMallId())
                .partnerName(request.getPartnerName())
                .partnerPhone(request.getPartnerPhone())
                .partnerRepresentative(request.getPartnerRepresentative())
                .decidePartnerType(DecidePartnerType.WAIT)
                .build();

        applyPartnerHistoryRepository.save(newData);

        return ResponseEntity.created(selfLink).build();
    }


}
