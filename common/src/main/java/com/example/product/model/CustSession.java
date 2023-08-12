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
@TypeAlias("custSession")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustSession {
    @Id
    private String sessionId;
    private Long custId;
}
