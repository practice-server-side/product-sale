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

    /**
     * 회원 정보 상세 조회
     * @return
     */
    @GetMapping("/information")
    public ResponseEntity<?> information() {
        return ResponseEntity.ok("");
    }

    /**
     * 회원 가입
     * @param request
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(
            @RequestBody CustRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        Cust newData = Cust.builder()
                .loginId(request.getLoginId())
                .loginPassword(passwordEncoder.encode(request.getLoginPassword()))
                .custName(request.getUserName())
                .custPhone(request.getUserPhone())
                .custKey(UUID.randomUUID().toString())
                .build();

        custRepository.save(newData);

        return ResponseEntity.created(selfLink).build();
    }


    /**
     * 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getLoginPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustSession newData = CustSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .custId((Long) authentication.getCredentials())
                .build();

        custSessionRepository.save(newData);

        return ResponseEntity.ok(
                LoginResponseDto.builder()
                        .session(newData.getSessionId())
                        .build()
        );
    }

}
