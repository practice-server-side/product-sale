package com.example.product.api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplyPartnerHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyPartnerHistoryId;

    private Long mallId;

    private String partnerName;
}
