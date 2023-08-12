package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cust extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column(unique = true)
    private String loginId;

    @Column
    private String loginPassword;

    @Column
    private String userName;

    @Column
    private String userPhone;

    @Column
    private String custKey;
}
