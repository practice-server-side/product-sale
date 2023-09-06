package com.example.product.api.repository;

import com.example.product.api.model.MallMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MallMemberRepository extends JpaRepository<MallMember, Long> {

}
