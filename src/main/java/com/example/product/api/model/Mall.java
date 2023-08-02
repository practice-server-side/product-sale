package com.example.product.api.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mallId;

    @Column
    private String mallName;

    @Column
    private String mallKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId")
    private Cust cust;
}
