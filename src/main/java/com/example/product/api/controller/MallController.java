package com.example.product.api.controller;

import com.example.product.api.dto.MallRegisterRequestDto;
import com.example.product.api.repository.MallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("project/v1/mall")
public class MallController {
    private final MallRepository mallRepository;

    @PostMapping
    public ResponseEntity<?> mallRegister(
            @RequestParam("custId") String custId,
            @RequestBody MallRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        return ResponseEntity.created(selfLink).build();
    }

}
