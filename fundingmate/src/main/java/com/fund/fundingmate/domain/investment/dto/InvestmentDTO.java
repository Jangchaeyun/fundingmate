package com.fund.fundingmate.domain.investment.dto;

import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.global.file.dto.FileDTO;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String investCategory;


    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;

    private FileDTO investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

    private List<FileDTO> investContentImgSavedName;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investProjContent;

    private FileDTO investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

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
    private List<InvestTypeDTO> investTypes;


}
