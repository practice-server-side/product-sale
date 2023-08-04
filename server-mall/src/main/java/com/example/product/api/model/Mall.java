package com.example.product.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mall extends CommonDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mallId;

    @Column
    private String mallName;

    @Column
    private String mallKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custId")
    private Cust cust;

}
