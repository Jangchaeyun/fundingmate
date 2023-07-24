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
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {

    private Long id;

    private String investCategory;

    private Integer investTargetAmount;

    private String investProjName;

    private String investProjKeyword;

    private Date investProjDateStart;

    private Date investProjDateEnd;

    private String investVideoUrl;

    private String investProjContent;

    private String businessAddress;

    private String bank;

    private String accNumber;

    private String depositorName;

    private String taxBillEmail;

    private String websiteUrl;

    private String facebookUrl;

    private String instagramUrl;

    private String blogUrl;

    private String twitterUrl;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investEmail;


    private String investRepImgSavedName;

    private String investContentImgSavedName;

    private String investIdBusinessLicenseImgSavedName;

    private String investBankAccountCopyImgSavedName;

/*
    private Integer userNo;*/
    private UserDTO user;

    private List<InvestTypeDTO> investTypes = new ArrayList<>();


}
