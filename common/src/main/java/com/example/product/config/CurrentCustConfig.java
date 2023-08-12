package com.example.product.config;

import com.example.product.annotation.Cust;
import com.example.product.model.CustSession;
import com.example.product.repository.CustSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.*;

@RequiredArgsConstructor
@Configuration
public class CurrentCustConfig implements HandlerMethodArgumentResolver {

    private final CustSessionRepository custSessionRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(com.example.product.dto.CurrentCust.class) &&
                parameter.hasParameterAnnotation(Cust.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        String sessionId = webRequest.getHeader("Authorization");

        CustSession custSession = CustSession.builder().build();


        if (Objects.nonNull(sessionId)) {
            custSession = custSessionRepository.findById(sessionId)
                    .orElse(CustSession.builder().build());
        }

        return com.example.product.dto.CurrentCust.builder()
                .custId(custSession.getCustId())
                .build();
    }
}
