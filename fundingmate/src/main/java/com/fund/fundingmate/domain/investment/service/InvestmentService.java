package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.user.dto.UserDTO;

import java.io.IOException;
import java.util.Map;

public interface InvestmentService {
   public void createInvestment(InvestmentDTO investmentDto, Long userId) throws IOException;
   public void createInvestWithUser(InvestmentDTO investmentDTO, UserDTO userDTO) throws IOException;
   public Map<String, Object> getInvestmentById(Long investmentId);

}
