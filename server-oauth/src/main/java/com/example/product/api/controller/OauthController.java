package com.example.product.api.controller;

import com.example.product.api.dto.CustRegisterRequestDto;
import com.example.product.api.dto.LoginRequestDto;
import com.example.product.api.dto.LoginResponseDto;
import com.example.product.api.model.Cust;
import com.example.product.api.repository.CustRepository;
import com.example.product.config.ApiAuthenticationProvider;
import com.example.product.model.CustSession;
import com.example.product.repository.CustSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/server-oauth/v1")
public class OauthController {
    private final ApiAuthenticationProvider authenticationManager;
    private final CustSessionRepository custSessionRepository;
    private final CustRepository custRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<?> custRegister(
            @RequestBody CustRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust newCust = Cust.builder()
                .loginId(request.getLoginId())
                .loginPassword(passwordEncoder.encode(request.getLoginPassword()))
                .userName(request.getUserName())
                .userPhone(request.getUserPhone())
                .custKey(UUID.randomUUID().toString())
                .build();

        custRepository.save(newCust);

        return ResponseEntity.created(selfLink).build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getLoginPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustSession custSession = CustSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .custId((Long) authentication.getCredentials())
                .build();

        custSessionRepository.save(custSession);

        return ResponseEntity.ok(
                LoginResponseDto.builder()
                        .session(custSession.getSessionId())
                        .build()
        );
    }

}
