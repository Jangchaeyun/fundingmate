package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.user.dto.UserDTO;

import java.util.Map;

public interface InvestmentService {
   public void createInvestment(InvestmentDTO investmentDto, Long userId);
   public void createInvestWithUser(InvestmentDTO investmentDTO, UserDTO userDTO);
   public Map<String, Object> getRewardById(Long rewardJd);

}
