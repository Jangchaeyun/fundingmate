package com.fund.fundingmate.domain.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvestTypeDTO {
    private Long id;

    private Integer investAmount;

    private Boolean investLimit;

    private Integer investLimitCount;

    private InvestmentDTO investmentDTO;
}
