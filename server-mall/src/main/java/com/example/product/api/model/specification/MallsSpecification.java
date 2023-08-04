package com.example.product.api.model.specification;

import com.example.product.api.dto.CustMallsPagingRequestDto;
import com.example.product.api.model.Mall;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class MallsSpecification {
    public static Specification<Mall> getMallSpecification(CustMallsPagingRequestDto requestDto) {
        Specification<Mall> specifications = Specification.where(null);
        //TODO : 커스트 아이디 추가
        return specifications;
    }
}
