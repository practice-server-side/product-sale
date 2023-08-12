package com.example.product.api.controller;

import com.example.product.annotation.CurrentCust;
import com.example.product.api.dto.cust.CustMallsPagingRequestDto;
import com.example.product.api.dto.cust.CustMallsPagingResponseDto;
import com.example.product.api.dto.mall.MallInfoResponseDto;
import com.example.product.api.dto.mall.MallRegisterRequestDto;
import com.example.product.api.dto.mall.MallRegisterResponseDto;
import com.example.product.exception.NotFoundException;
import com.example.product.exception.UnAuthorizationException;
import com.example.product.api.model.Cust;
import com.example.product.api.model.Mall;
import com.example.product.api.model.specification.MallsSpecification;
import com.example.product.api.repository.CustRepository;
import com.example.product.api.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/server-mall/v1/mall")
public class MallController {
    private final MallRepository mallRepository;
    private final CustRepository custRepository;
    private final MessageSourceAccessor messageSource;

    /**
     * 회원 몰 리스트 조회
     */
    @GetMapping
    public ResponseEntity<?> custMalls(@ModelAttribute CustMallsPagingRequestDto request) {

        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getPageSize(), Sort.Direction.DESC, request.getSort());
        Specification<Mall> specification = MallsSpecification.getMallSpecification(request);
        Page<Mall> dataList = mallRepository.findAll(specification, pageRequest);

        return ResponseEntity.ok(
                CustMallsPagingResponseDto.builder()
                        .totalCount(dataList.getTotalElements())
                        .page(dataList.getNumber())
                        .pageSize(dataList.getSize())
                        .malls(
                                dataList.stream()
                                        .map(mall -> CustMallsPagingResponseDto.Malls.builder()
                                                .mallId(mall.getMallId())
                                                .mallName(mall.getMallName())
                                                .mallKey(mall.getMallKey())
                                                .registerDate(mall.getRegisterDate())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build()
        );
    }

    /**
     * 몰 상세 조회
     */
    @GetMapping("/{mallId}")
    public ResponseEntity<?> mallInfo(
            @CurrentCust com.example.product.dto.CurrentCust currentCust,
            @PathVariable("mallId") Long mallId) {

        Mall mall = mallRepository.findById(mallId)
                .orElseThrow(() -> new NotFoundException("M01", messageSource.getMessage("M01")));

        if (!mall.getCust().getCustId().equals(currentCust.getCustId())) {
            throw new UnAuthorizationException("M02", messageSource.getMessage("M02"));
        }

        return ResponseEntity.ok(
                MallInfoResponseDto.builder()
                        .mallId(mall.getMallId())
                        .mallName(mall.getMallName())
                        .mallKey(mall.getMallKey())
                        .custKey(mall.getCust().getCustKey())
                        .build()
        );
    }

    /**
     * 몰 등록
     */
    @PostMapping
    public ResponseEntity<?> mallRegister(
            @RequestParam("custId") Long custId,
            @RequestBody MallRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust cust = custRepository.findById(custId)
                .orElseThrow(() -> new NotFoundException("C01", messageSource.getMessage("C01")));

        Mall newData = Mall.builder()
                .mallName(request.getMallName())
                .mallKey(UUID.randomUUID().toString())
                .cust(cust)
                .build();

        mallRepository.save(newData);

        return ResponseEntity.created(selfLink).body(
                MallRegisterResponseDto.builder()
                        .mallId(newData.getMallId())
                        .build()
        );
    }

}
