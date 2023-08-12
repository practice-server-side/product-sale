package com.example.product.api.model.specification;

import com.example.product.api.dto.cust.CustMallsPagingRequestDto;
import com.example.product.api.model.Mall;
import com.example.product.dto.CurrentCust;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
public class MallsSpecification {
    public static Specification<Mall> getMallSpecification(CurrentCust currentCust, CustMallsPagingRequestDto requestDto) {
        Specification<Mall> specifications = Specification.where(null);

        Long custId = currentCust.getCustId();
        specifications = specifications.and(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("cust").get("custId"), custId )
        );

        return specifications;
    }
}
