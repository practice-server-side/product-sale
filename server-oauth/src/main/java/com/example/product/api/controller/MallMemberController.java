package com.example.product.api.controller;

import com.example.product.api.dto.*;
import com.example.product.api.model.MallMember;
import com.example.product.api.repository.MallMemberRepository;
import com.example.product.config.ApiAuthenticationProvider;
import com.example.product.model.MemberSession;
import com.example.product.repository.MallMemberSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MallMemberController {
    private final ApiAuthenticationProvider authenticationManager;
    private final MallMemberRepository mallMemberRepository;
    private final MallMemberSessionRepository mallMemberSessionRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 몰 회원 정보 상세 조회
     * @return
     */
    @GetMapping("/information")
    public ResponseEntity<?> information() {
        return ResponseEntity.ok("");
    }

    /**
     * 몰 회원 가입
     * @param request
     * @return
     */
    @PostMapping("/join")
    public ResponseEntity<?> join(
            @RequestBody MallMemberRegisterRequestDto request) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());

        MallMember newData = MallMember.builder()
                .memberId(request.getMemberId())
                .memberPassword(passwordEncoder.encode(request.getPassword()))
                .build();

        mallMemberRepository.save(newData);

        return ResponseEntity.created(selfLink).build();
    }


    /**
     * 몰 회원 로그인
     * @param request
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody MallMemberLoginRequestDto request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getMemberId(), request.getLoginPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberSession newData = MemberSession.builder()
                .sessionId(UUID.randomUUID().toString())
                .memberId((Long) authentication.getCredentials())
                .build();

        mallMemberSessionRepository.save(newData);

        return ResponseEntity.ok(
                MallMemberLoginResponseDto.builder()
                        .session(newData.getSessionId())
                        .build()
        );
    }
}
