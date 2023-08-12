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
public class Partner extends CommonDate {
    @Id
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
