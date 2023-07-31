package com.example.product.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long custId;

    @Column
    private String userName;

    @Column
    private String userPhone;
}