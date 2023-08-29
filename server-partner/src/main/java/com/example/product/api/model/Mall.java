package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mall extends CommonDate {
    @Id
    private Long mallId;

    @Column(nullable = false)
    private String mallName;

    @Column(nullable = false)
    private String mallKey;

    @Column(nullable = false)
    private Long custId;
}
