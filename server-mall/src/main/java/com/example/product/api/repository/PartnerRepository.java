package com.example.product.api.repository;

import com.example.product.api.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {
    List<Partner> findByPartnerIdIn(List<Long> partnerIds);
}
