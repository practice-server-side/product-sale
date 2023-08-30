package com.example.product.api.model;


import com.example.product.enums.DecidePartnerType;
import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyPartnerHistory extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyPartnerHistoryId;

    @Column(nullable = false)
    private Long mallId;

    @Column(nullable = false)
    private String partnerName;

    @Column(nullable = false)
    private String partnerPhone;

    @Column
    private String partnerRepresentative;

    @Column(columnDefinition = "ENUM('WAIT', 'ACCEPT', 'REFUSE') NOT NULL")
    @Enumerated(EnumType.STRING)
    private DecidePartnerType decidePartnerType;

    @Column
    private String decideReason;
}
