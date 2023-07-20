package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.user.dto.UserDTO;

import java.io.IOException;
import java.util.Map;

public interface InvestmentService {

public Long createInvestment(InvestmentDTO investmentDto, Long userId);

   public Map<String, Object> getInvestmentById(Long investmentId);
}
