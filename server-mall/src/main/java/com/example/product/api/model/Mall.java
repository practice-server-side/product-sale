package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mall extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mallId;

    @Column(nullable = false)
    private String mallName;

    @Column(nullable = false)
    private String mallKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId", nullable = false)
    private Cust cust;

    @OneToMany(mappedBy = "mall", fetch = FetchType.LAZY)
    private List<Partner> partnerList;
}
