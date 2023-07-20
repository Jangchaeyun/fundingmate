package com.fund.fundingmate.domain.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardTypeDTO {
    private Long id;
    private Integer rewardAmount;
    private Boolean rewardAvailableLimit;
    private Integer rewardAvailableCount;
    private String rewardTitle;
    private String rewardContent;
    private Boolean rewardShipAddress;
    private RewardOptionDTO rewardOptions;
}