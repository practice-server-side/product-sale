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
public class Mall extends CommonDate {
    @Id
    private Long mallId;

    @Column
    private String mallName;

    @Column
    private String mallKey;

    @Column
    private Long custId;
}
