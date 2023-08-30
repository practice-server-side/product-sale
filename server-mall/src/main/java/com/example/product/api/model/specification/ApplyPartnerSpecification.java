package com.example.product.api.model.specification;

import com.example.product.api.dto.partner.ApplyPartnerPagingRequestDto;
import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.dto.CurrentCust;
import com.example.product.enums.DecidePartnerType;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;


public class ApplyPartnerSpecification {
    public static Specification<ApplyPartnerHistory> getApplyPartnerSpecification(CurrentCust currentCust, ApplyPartnerPagingRequestDto request) {
        Specification<ApplyPartnerHistory> specifications = Specification.where(null);

        specifications = specifications.and(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("mallId"), request.getMallId())
        );

        if (request.getSearchType().equals("DecidePartnerType") && StringUtils.isNotBlank(request.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("decidePartnerType"), DecidePartnerType.valueOf(request.getKeyword()))
            );
        }


        return specifications;
    }
}
