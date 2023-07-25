package com.fund.fundingmate.domain.investment.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.global.file.dto.FileDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO2 {

    private Long id;

    private String investCategory;

    private String investProjName;

    private Integer investTargetAmount;

    private Date investProjDateStart;

    private Date investProjDateEnd;

    private MultipartFile investRepImgSavedName;

    private String investProjKeyword;

    private String useOfFunds;

    private Date useOfFundsDateStart;

    private Date useOfFundsDateEnd;

    private Integer rateOfReturn;

    private Date expectedPaymentDate;

    private String repaymentMethod;

    private String investVideoUrl;

    private MultipartFile[] investContentImgSavedName;

    private String investItemIntro;

    private String investItemBusinessValue;

    private String investItemValue;

    private String investItemBenefit;

    private String investProjContent;

    private MultipartFile investIdBusinessLicenseImgSavedName;

    private String businessAddress;

    private String investEmail;

    private String bank;

    private String accNumber;

    private String depositorName;

    private MultipartFile investBankAccountCopyImgSavedName;

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
