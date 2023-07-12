package com.fund.fundingmate.domain.investment.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {
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

    private String websiteUrl;

    private String facebookUrl;

    private String instagramUrl;

    private String blogUrl;

    private String twitterUrl;
/*
    private Integer userNo;*/
    private UserDTO user;
}
