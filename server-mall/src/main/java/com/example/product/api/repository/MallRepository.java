package com.example.product.api.repository;

import com.example.product.api.model.Mall;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MallRepository extends JpaRepository<Mall, Long> {
    Page<Mall> findAll(Specification<Mall> specification, Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"cust", "partnerList"})
    Optional<Mall> findById(Long mallId);

}
