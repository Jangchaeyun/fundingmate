package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface InvestmentService {

   public Long createInvestment(InvestmentDTO investmentDTO, Long userId, String cards, MultipartFile reqFile, MultipartFile[] contentFiles, MultipartFile businessFile, MultipartFile bankFile) throws Exception;
   public Map<String, Object> getInvestmentById(Long investmentId);
}