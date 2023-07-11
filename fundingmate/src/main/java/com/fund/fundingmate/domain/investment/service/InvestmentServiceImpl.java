package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.investment.repository.InvestmentRepository;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InvestmentServiceImpl implements InvestmentService {


    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public void createInvestment(InvestmentDTO investmentDto, Long userId) {
        Investment investment = new Investment();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        investment.setUser(user);
        // Set investment properties from investmentDto
        investment.setInvestNo(investmentDto.getInvestNo());
        investment.setInvestCategory(investmentDto.getInvestCategory());
        investment.setInvestProjName(investmentDto.getInvestProjName());
        investment.setInvestTargetAmount(investmentDto.getInvestTargetAmount());
        investment.setInvestProjDateStart(investmentDto.getInvestProjDateStart());
        investment.setInvestProjDateEnd(investmentDto.getInvestProjDateEnd());
        investment.setInvestRepImgSavedName(investmentDto.getInvestRepImgSavedName());
        investment.setInvestProjKeyword(investmentDto.getInvestProjKeyword());
        investment.setUseOfFunds(investmentDto.getUseOfFunds());
        investment.setUseOfFundsDateStart(investmentDto.getUseOfFundsDateStart());
        investment.setUseOfFundsDateEnd(investmentDto.getUseOfFundsDateEnd());
        investment.setRateOfReturn(investmentDto.getRateOfReturn());
        investment.setExpectedPaymentDate(investmentDto.getExpectedPaymentDate());
        investment.setRepaymentMethod(investmentDto.getRepaymentMethod());
        investment.setInvestVideoUrl(investmentDto.getInvestVideoUrl());
        investment.setInvestContentImgSavedName(investmentDto.getInvestContentImgSavedName());
        investment.setInvestItemIntro(investmentDto.getInvestItemIntro());
        investment.setInvestItemBusinessValue(investmentDto.getInvestItemBusinessValue());
        investment.setInvestItemValue(investmentDto.getInvestItemValue());
        investment.setInvestItemBenefit(investmentDto.getInvestItemBenefit());
        investment.setInvestProjContent(investmentDto.getInvestProjContent());
        investment.setInvestIdBusinessLicenseImgSavedName(investmentDto.getInvestIdBusinessLicenseImgSavedName());
        investment.setBusinessAddress(investmentDto.getBusinessAddress());
        investment.setInvestEmail(investmentDto.getInvestEmail());
        investment.setBank(investmentDto.getBank());
        investment.setAccNumber(investmentDto.getAccNumber());
        investment.setDepositorName(investmentDto.getDepositorName());
        investment.setInvestBankAccountCopyImgSavedName(investmentDto.getInvestBankAccountCopyImgSavedName());
        investment.setTaxBillEmail(investmentDto.getTaxBillEmail());
        investment.setWebsiteUrl(investmentDto.getWebsiteUrl());
        investment.setFacebookUrl(investmentDto.getFacebookUrl());
        investment.setInstagramUrl(investmentDto.getInstagramUrl());
        investment.setBlogUrl(investmentDto.getBlogUrl());
        investment.setTwitterUrl(investmentDto.getTwitterUrl());

        // Set user reference
       /* User user = userRepository.findByUserNo(userNo);
        if (user == null) {
            // Handle user not found error
            throw new RuntimeException("User not found");
        }
        investment.setUser(user);*/


        // Save the investment in the database
        investmentRepository.save(investment);
    }
}