package com.fund.fundingmate.domain.investment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InvestTypeDTO {
    private Long id;

    private Integer investAmount;

    private Boolean investLimit;

    private Integer investLimitCount;

    private InvestmentDTO investmentDTO;
}
