package com.example.product.api.repository;

import com.example.product.api.model.ApplyProductHistory;
import com.example.product.enums.DecideProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyProductHistoryRepository extends JpaRepository<ApplyProductHistory, Long> {
    @EntityGraph(attributePaths = {"partner"})
    List<ApplyProductHistory> findByApplyProductHistoryIdInAndDecideProductType(List<Long> ids, DecideProductType decideProductType);

    Page<ApplyProductHistory> findAll(Specification<ApplyProductHistory> specification, Pageable pageable);
}
