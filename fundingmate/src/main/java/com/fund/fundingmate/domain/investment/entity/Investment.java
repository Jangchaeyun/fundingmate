package com.fund.fundingmate.domain.investment.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investment")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"investTypes"})
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String investCategory;

    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;
    private Long investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

    private String  investContentImgSavedName;  //파일번호 목록: 1,2,3

    @Column(columnDefinition = "VARCHAR(600)")
    private String investItemIntro;

    @Column(columnDefinition = "VARCHAR(600)")
    private String investItemBusinessValue;

    @Column(columnDefinition = "VARCHAR(600)")
    private String investItemValue;

    @Column(columnDefinition = "VARCHAR(600)")
    private String investItemBenefit;

    @Column(length = 100000)
    private String investProjContent;

    private Long investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

    private Long investBankAccountCopyImgSavedName;

    private String taxBillEmail;


    private String websiteUrl;


    private String facebookUrl;


    private String instagramUrl;


    private String blogUrl;


    private String twitterUrl;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @OneToMany(mappedBy = "investment", cascade = CascadeType.ALL)
    private List<InvestType> investTypes = new ArrayList<>();

}
