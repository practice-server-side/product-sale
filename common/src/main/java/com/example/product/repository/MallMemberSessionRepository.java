package com.example.product.repository;

import com.example.product.model.MemberSession;
import org.springframework.data.repository.CrudRepository;

public interface MallMemberSessionRepository extends CrudRepository<MemberSession, String> {
}
