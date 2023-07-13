package com.fund.fundingmate.domain.investment.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.global.file.dto.FileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {
    private Long id;

    private String investCategory;

    @JsonProperty("projName")
    private String investProjName;
    @JsonProperty("projTargetAmount")
    private Integer investTargetAmount;
    @JsonProperty("projDateStart")
    private Date investProjDateStart;
    @JsonProperty("projDateEnd")
    private Date investProjDateEnd;
    @JsonProperty("imageFile")
    private FileDTO investRepImgSavedName;
    @JsonProperty("projKeyword")
    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;
    @JsonProperty("inputs")
    private String investVideoUrl;
    @JsonProperty("projImages")
    private FileDTO investContentImgSavedName;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;
    @JsonProperty("projContent")
    private String investProjContent;
    @JsonProperty("rewardIdBusinessLicenseImgSavedName")
    private FileDTO investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;
    @JsonProperty("rewardBankAccountCopyImgSavedName")
    private FileDTO investBankAccountCopyImgSavedName;

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
