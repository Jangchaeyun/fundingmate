package com.fund.fundingmate.domain.investment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invest_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer investAmount;
    @Column
    private Boolean investLimit;
    @Column
    private Integer investLimitCount;

    @ManyToOne
    @JoinColumn(name = "investment_id", referencedColumnName = "id")
    private Investment investment;
}
