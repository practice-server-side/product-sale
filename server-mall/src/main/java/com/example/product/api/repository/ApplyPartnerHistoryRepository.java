package com.example.product.api.repository;

import com.example.product.api.model.ApplyPartnerHistory;
import com.example.product.enums.DecidePartnerType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyPartnerHistoryRepository extends JpaRepository<ApplyPartnerHistory, Long> {
    Page<ApplyPartnerHistory> findAll(Specification<ApplyPartnerHistory> specification, Pageable pageable);

    Optional<ApplyPartnerHistory> findByApplyPartnerHistoryIdAndDecidePartnerType(Long id, DecidePartnerType type);

}
