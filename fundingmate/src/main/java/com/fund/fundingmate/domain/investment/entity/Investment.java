package com.fund.fundingmate.domain.investment.entity;

import com.fund.fundingmate.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "investment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investNo;

    private String investCategory;

    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;

    private String investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

    private String investContentImgSavedName;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investProjContent;

    private String investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

    private String investBankAccountCopyImgSavedName;

    private String taxBillEmail;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String facebookUrl;

    @Column(nullable = false)
    private String instagramUrl;

    @Column(nullable = false)
    private String blogUrl;

    @Column(nullable = false)
    private String twitterUrl;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
