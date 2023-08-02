package com.example.product.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mall {
    @Id
    private Long mallId;

    private String mallName;

    private String mallKey;

    @ManyToOne
    @JoinColumn(name = "custId")
    private Cust cust;
}
