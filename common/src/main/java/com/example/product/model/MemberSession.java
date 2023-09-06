package com.example.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
@TypeAlias("mallMemberSession")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSession {
    @Id
    private String sessionId;
    private Long memberId;
}
