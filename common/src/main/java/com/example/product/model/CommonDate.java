package com.example.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonDate implements Serializable {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime registerDate;

    @LastModifiedDate
    @Column
    private LocalDateTime updateDt;
}
