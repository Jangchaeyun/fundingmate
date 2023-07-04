package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardDTO {
    private Long rewardNo;
    private String projName;
    private Integer projTargetAmout;
    private LocalDate projDateStart;
    private LocalDate projDateEnd;
    private String rewardRepImgSavedName;
    private String projKeyWord;
    private String rewardVideoAddress;
    private String rewardContentImgSavedName;
    private String projContent;
    private String rewardRefundExchangePolicy;
    private String rewardContact;
    private String rewardEmail;
    private String rewardCategory;
    private String modelName;
    private String countryOfOrigin;
    private String manufacturer;
    private String asPhoneNumber;
    private String rewardIdBusinessLicenseImgSavedName;
    private String businessAddress;
    private String bank;
    private String accNumber;
    private String depositorName;
    private String rewardBankAccountCopyImgSavedName;
    private String taxBillEmail;
    private String websiteUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String blogUrl;
    private String twitterUrl;
    private UserDTO user;
    private List<RewardTypeDTO> rewardTypes;
}