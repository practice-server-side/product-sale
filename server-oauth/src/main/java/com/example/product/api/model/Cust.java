package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cust extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column
    private String loginId;

    @Column
    private String loginPassword;

    @Column
    private String userName;

    @Column
    private String userPhone;

    @Column
    private String clientKey;
}