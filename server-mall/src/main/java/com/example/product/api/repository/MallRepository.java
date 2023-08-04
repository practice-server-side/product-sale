package com.example.product.api.repository;

import com.example.product.api.model.Mall;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallRepository extends JpaRepository<Mall, Long> {

}
