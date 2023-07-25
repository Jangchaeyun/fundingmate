package com.fund.fundingmate.domain.reward.dto;

import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.global.file.dto.FileDTO;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RewardDTO {
    private Long id;
    private String rewardCategory;
    private String projName;
    private Integer projTargetAmount;
    private Date projDateStart;
    private Date projDateEnd;
    private Long rewardBankAccountCopyImgSavedName;
    private Date deliveryDate;
    private String projKeyWord;
    private String rewardVideoAddress;
    private Long rewardIdBusinessLicenseImgSavedName;
    private String projContent;
    private String rewardRefundExchangePolicy;
    private String rewardContact;
    private String rewardEmail;
    private String modelName;
    private String countryOfOrigin;
    private String manufacturer;
    private String rewardLaw;
    private String asPhoneNumber;
    private Long rewardRepImgSavedName;
    private String businessAddress;
    private String bank;
    private String accNumber;
    private String depositorName;
    private String taxBillEmail;
    private String rewardContentImgSavedName;
    private String websiteUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String blogUrl;
    private String twitterUrl;
    private UserDTO user;
    private List<RewardTypeDTO> rewardTypes;
}