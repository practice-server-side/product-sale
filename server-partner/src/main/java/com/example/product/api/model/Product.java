package com.example.product.api.model;

import com.example.product.model.CommonDate;
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
public class Product extends CommonDate {
    @Id
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private String productPrice;

    @Column(nullable = false)
    private String imageUrl1;

    @Column
    private String imageUrl2;

    @ManyToOne
    @JoinColumn(name = "partnerId", nullable = false)
    private Partner partner;
}
