package com.fund.fundingmate.domain.investment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "invest_reply")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repNo;

    private String repTitle;

    private String repContent;

    private Date repRegistrationDate;

    private Date repRevisionDate;

    @ManyToOne
    @JoinColumn(name = "invest_no")
    private Investment investment;

    @ManyToOne
    @JoinColumn(name = "comm_no")
    private InvestComment comment;
}
