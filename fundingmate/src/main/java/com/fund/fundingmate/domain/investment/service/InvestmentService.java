package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.Investment;
public interface InvestmentService {
   public void createInvestment(InvestmentDTO investmentDto, Long userId);
}
