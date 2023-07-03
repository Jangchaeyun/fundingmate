package com.fund.fundingmate.domain.investment.entity;

import javax.persistence.*;

@Entity
@Table(name = "invest_type")
public class InvestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investOptNo;

    private String investAmount;

    private String investLimit;

    private String investLimitCount;

    @ManyToOne
    @JoinColumn(name = "invest_no")
    private Investment investment;
}
