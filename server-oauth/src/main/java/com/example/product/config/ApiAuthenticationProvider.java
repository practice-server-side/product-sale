package com.example.product.config;

import com.example.product.api.model.CustomUserDetails;
import com.example.product.api.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationProvider implements AuthenticationManager {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        String loginId = authentication.getName();
        String loginPassword = (String) authentication.getCredentials();

        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(loginId);
        if (!passwordEncoder.matches(user.getPassword(), loginPassword)) {
            throw new IllegalAccessError();
        }

        return new UsernamePasswordAuthenticationToken(loginId, null, user.getAuthorities());
    }
}