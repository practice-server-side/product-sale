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
public class Partner extends CommonDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    @Column
    private String partnerName;

    @Column
    private String partnerPhone;

    @Column
    private String partnerRepresentative;

    @ManyToOne
    @JoinColumn(name = "mallId")
    private Mall mall;
}
