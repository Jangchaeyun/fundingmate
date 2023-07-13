package com.fund.fundingmate.domain.investment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invest_type")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer investAmount;

    private Boolean investLimit;

    private Integer investLimitCount;

    @ManyToOne
    @JoinColumn(name = "invest_no")
    private Investment investment;
}
