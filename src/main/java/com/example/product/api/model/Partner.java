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
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    private String partnerName;

    private String partnerPhone;

    private String partnerRepresentative;

    private String partnerKey;

    @ManyToOne
    @JoinColumn(name = "mallId")
    private Mall mall;
}
