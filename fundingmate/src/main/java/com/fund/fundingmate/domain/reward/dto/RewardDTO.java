package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.global.file.dto.FileDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardDTO {
    private Long id;
    private String projName;
    private Integer projTargetAmount;
    private LocalDate projDateStart;
    private LocalDate projDateEnd;
    private LocalDate deliveryDate;
    private FileDTO rewardContentImgSavedName;
    private String projKeyWord;
    private String rewardVideoAddress;
    private FileDTO rewardRepImgSavedName;
    private String projContent;
    private String rewardRefundExchangePolicy;
    private String rewardContact;
    private String rewardEmail;
    private String rewardCategory;
    private String modelName;
    private String countryOfOrigin;
    private String manufacturer;
    private String rewardLaw;
    private String asPhoneNumber;
    private FileDTO rewardIdBusinessLicenseImgSavedName;
    private String businessAddress;
    private String bank;
    private String accNumber;
    private String depositorName;
    private FileDTO rewardBankAccountCopyImgSavedName;
    private String taxBillEmail;
    private String websiteUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String blogUrl;
    private String twitterUrl;
    private UserDTO user;
    private List<RewardTypeDTO> rewardTypes;
}