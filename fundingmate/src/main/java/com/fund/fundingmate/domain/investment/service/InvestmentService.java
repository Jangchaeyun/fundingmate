package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;

public interface InvestmentService {
   public void createInvestment(InvestmentDTO investmentDto, Long userId);
}
