package com.example.product.api.service;

import com.example.product.api.model.Cust;
import com.example.product.api.model.CustomUserDetails;
import com.example.product.api.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustRepository custRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Cust> cust = custRepository.findByLoginId(loginId);

        try {
            if (loginId.equals(cust.get().getLoginId())) {
                return CustomUserDetails.builder()
                        .custId(cust.get().getCustId())
                        .loginId(cust.get().getLoginId())
                        .loginPassword(cust.get().getLoginPassword()) // 비밀번호 암호화
                        .build();
            }
        } catch (Exception e){
            throw new UsernameNotFoundException(loginId + "가 존재하지 않습니다");
        }

        throw new UsernameNotFoundException(loginId + "가 존재하지 않습니다");
    }
}
