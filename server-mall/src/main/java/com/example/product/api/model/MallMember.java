package com.example.product.api.model;

import com.example.product.model.CommonDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MallMember extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column
    private String memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mallId")
    private Mall mall;

    @OneToMany(mappedBy = "mallMember")
    private List<MallMemberGrade> mallMemberGrades;

    @OneToMany(mappedBy = "mallMember")
    private List<MallMemberGroup> mallMemberGroups;
}
