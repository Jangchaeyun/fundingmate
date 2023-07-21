package com.fund.fundingmate.domain.reward.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardOptionDTO {
    private Long id;
    private String rewardOptName;
    private String rewardOptCon;
    private RewardTypeDTO rewardType;
}