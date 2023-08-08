package com.example.product.api.repository;

import com.example.product.api.model.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustRepository extends JpaRepository<Cust, Long> {
    Optional<Cust> findByLoginId(String loginId);
}
