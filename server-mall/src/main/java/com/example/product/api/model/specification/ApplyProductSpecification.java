package com.example.product.api.model.specification;

import com.example.product.api.dto.product.ApplyProductPagingRequestDto;
import com.example.product.api.model.ApplyProductHistory;
import com.example.product.dto.CurrentCust;
import com.example.product.enums.DecidePartnerType;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class ApplyProductSpecification {
    public static Specification<ApplyProductHistory> getApplyProductSpecification(CurrentCust currentCust, ApplyProductPagingRequestDto request) {
        Specification<ApplyProductHistory> specifications = Specification.where(null);

        specifications = specifications.and(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("partner").get("partnerId"), request.getPartnerId())
        );

        if (request.getSearchType().equals("DecideProductType") && StringUtils.isNotBlank(request.getKeyword())) {
            specifications = specifications.and(
                    (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("decideProductType"), DecidePartnerType.valueOf(request.getKeyword()))
            );
        }


        return specifications;
    }
}
