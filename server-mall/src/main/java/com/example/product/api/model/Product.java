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
public class Product extends CommonDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column
    private String productName;

    @Column
    private String productPrice;

    @ManyToOne
    @JoinColumn(name = "partnerId")
    private Partner partner;
}
