package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MallMemberGrade extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gradeNo;

    @Column(nullable = false)
    private String gradeName;

    @Column
    private Integer discountPercentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private MallMember mallMember;
}
