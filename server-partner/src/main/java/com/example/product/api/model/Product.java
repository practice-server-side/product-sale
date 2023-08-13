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

    @Column
    private String productName;

    @Column
    private String productPrice;

    @ManyToOne
    @JoinColumn(name = "partnerId")
    private Partner partner;
}