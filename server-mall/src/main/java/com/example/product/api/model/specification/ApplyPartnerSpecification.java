package com.example.product.api.model.specification;

import com.example.product.api.dto.cust.CustMallsPagingRequestDto;
import com.example.product.api.dto.partner.ApplyPartnerPagingRequestDto;
import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.dto.CurrentCust;
import org.springframework.data.jpa.domain.Specification;

public class ApplyPartnerSpecification {
    public static Specification<ApplyPartnerHistory> getApplyPartnerSpecification(CurrentCust currentCust, ApplyPartnerPagingRequestDto requestDto) {
        Specification<ApplyPartnerHistory> specifications = Specification.where(null);

        specifications = specifications.and(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("mallId"), requestDto.getMallId())
        );

        return specifications;
    }
}
