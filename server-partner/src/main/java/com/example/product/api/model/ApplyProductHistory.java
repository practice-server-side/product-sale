package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ApplyProductHistory extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyProductHistoryId;

    @Column
    private String productName;

    @Column
    private Integer productPrice;

    @Column
    private String imageUrl1;

    @Column
    private String imageUrl2;

    @ManyToOne
    private Partner partnerId;
}
