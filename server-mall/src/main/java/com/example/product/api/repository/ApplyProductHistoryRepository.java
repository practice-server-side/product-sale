package com.example.product.api.repository;

import com.example.product.api.model.ApplyProductHistory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyProductHistoryRepository extends JpaRepository<ApplyProductHistory, Long> {
    @EntityGraph(attributePaths = {"partner"})
    List<ApplyProductHistory> findByApplyProductHistoryIdIn(List<Long> ids);
}
