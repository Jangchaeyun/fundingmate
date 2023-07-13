package com.fund.fundingmate.domain.investment.service;

import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.InvestType;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.investment.repository.InvestmentRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Service
@Transactional
public class InvestmentServiceImpl implements InvestmentService {


    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void createInvestment(InvestmentDTO investmentDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        //Investment investment = new Investment();
        Investment investment = convertToInvestment(investmentDTO);

        File investBankAccountCopyImgSavedName = converToFile(investmentDTO.getInvestBankAccountCopyImgSavedName());
        investment.setInvestBankAccountCopyImgSavedName(investBankAccountCopyImgSavedName);
        investBankAccountCopyImgSavedName = fileRepository.save(investBankAccountCopyImgSavedName);

        File investIdBusinessLicenseImgSavedName = converToFile(investmentDTO.getInvestIdBusinessLicenseImgSavedName());
        investment.setInvestIdBusinessLicenseImgSavedName(investIdBusinessLicenseImgSavedName);
        investIdBusinessLicenseImgSavedName = fileRepository.save(investIdBusinessLicenseImgSavedName);

        File investRepImgSavedName = converToFile(investmentDTO.getInvestRepImgSavedName());
        investment.setInvestRepImgSavedName(investRepImgSavedName);
        investRepImgSavedName = fileRepository.save(investRepImgSavedName);

        File investContentImgSavedName = converToFile(investmentDTO.getInvestContentImgSavedName());
        investment.setInvestContentImgSavedName(investContentImgSavedName);
        investContentImgSavedName = fileRepository.save(investContentImgSavedName);


        investment.setInvestBankAccountCopyImgSavedName(investBankAccountCopyImgSavedName);
        investment.setInvestIdBusinessLicenseImgSavedName(investIdBusinessLicenseImgSavedName);
        investment.setInvestRepImgSavedName(investRepImgSavedName);
        investment.setInvestContentImgSavedName(investContentImgSavedName);

        investment.setUser(user);

        investmentRepository.save(investment);
    }


    public void createInvestWithUser(InvestmentDTO investmentDTO, UserDTO userDTO) {
        User user = convertToUser(userDTO);
        userRepository.save(user);

        Investment investment = convertToInvestment(investmentDTO);
        investment.setUser(user);

        investmentRepository.save(investment);
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserid(userDTO.getUserid());
        user.setPassword(userDTO.getPassword());
        // Set other properties of User entity from UserDTO
        return user;
    }

    private File converToFile(FileDTO fileDTO) {
        if (fileDTO == null) {
            return null;
        }

        File file = new File();
        file.setFileId(fileDTO.getFileId());
        file.setFileName(fileDTO.getFileName());
        file.setFileRegistrationDate(fileDTO.getFileRegistrationDate());
        return file;
    }

    private Investment convertToInvestment(InvestmentDTO investmentDTO) {
        Investment investment = new Investment();
        investment.setInvestCategory(investmentDTO.getInvestCategory());
        investment.setInvestProjName(investmentDTO.getInvestProjName());
        investment.setInvestTargetAmount(investmentDTO.getInvestTargetAmount());
        investment.setInvestProjDateStart(investmentDTO.getInvestProjDateStart());
        investment.setInvestProjDateEnd(investmentDTO.getInvestProjDateEnd());

        FileDTO ibacisnFileDTO = investmentDTO.getInvestBankAccountCopyImgSavedName();
        if (ibacisnFileDTO != null) {
            File investBankAccountCopyImgSavedName = converToFile(ibacisnFileDTO);
            investment.setInvestBankAccountCopyImgSavedName(investBankAccountCopyImgSavedName);
        }

        investment.setInvestProjKeyword(investmentDTO.getInvestProjKeyword());
        investment.setUseOfFunds(investmentDTO.getUseOfFunds());

        FileDTO iiblisnDTO = investmentDTO.getInvestIdBusinessLicenseImgSavedName();
        if (iiblisnDTO != null) {
            File investIdBusinessLicenseImgSavedName = converToFile(iiblisnDTO);
            investment.setInvestIdBusinessLicenseImgSavedName(investIdBusinessLicenseImgSavedName);
        }

        investment.setUseOfFundsDateStart(investmentDTO.getUseOfFundsDateStart());
        investment.setUseOfFundsDateEnd(investmentDTO.getUseOfFundsDateEnd());
        investment.setRateOfReturn(investmentDTO.getRateOfReturn());
        investment.setExpectedPaymentDate(investmentDTO.getExpectedPaymentDate());
        investment.setRepaymentMethod(investmentDTO.getRepaymentMethod());
        investment.setInvestVideoUrl(investmentDTO.getInvestVideoUrl());
        investment.setInvestItemIntro(investmentDTO.getInvestItemIntro());
        investment.setInvestItemBusinessValue(investmentDTO.getInvestItemBusinessValue());
        investment.setInvestItemValue(investmentDTO.getInvestItemValue());
        investment.setInvestItemBenefit(investmentDTO.getInvestItemBenefit());
        investment.setInvestProjContent(investmentDTO.getInvestProjContent());

        FileDTO irisnDTO = investmentDTO.getInvestRepImgSavedName();
        if (irisnDTO != null) {
            File investRepImgSavedName = converToFile(irisnDTO);
            investment.setInvestRepImgSavedName(investRepImgSavedName);
        }

        investment.setBusinessAddress(investmentDTO.getBusinessAddress());
        investment.setInvestEmail(investmentDTO.getInvestEmail());
        investment.setBank(investmentDTO.getBank());
        investment.setAccNumber(investmentDTO.getAccNumber());
        investment.setDepositorName(investmentDTO.getDepositorName());

        FileDTO ciisnDTO = investmentDTO.getInvestContentImgSavedName();
        if (ciisnDTO != null) {
            File investContentImgSavedName = converToFile(ciisnDTO);
            investment.setInvestContentImgSavedName(investContentImgSavedName);
        }

        investment.setTaxBillEmail(investmentDTO.getTaxBillEmail());
        investment.setWebsiteUrl(investmentDTO.getWebsiteUrl());
        investment.setFacebookUrl(investmentDTO.getFacebookUrl());
        investment.setInstagramUrl(investmentDTO.getInstagramUrl());
        investment.setBlogUrl(investmentDTO.getBlogUrl());
        investment.setTwitterUrl(investmentDTO.getTwitterUrl());
        UserDTO userDTO = investmentDTO.getUser();
        if (userDTO != null) {
            User user = convertToUser(userDTO);
            investment.setUser(user);
        }
        List<InvestType> investTypes = convertToInvestType(investmentDTO.getInvestTypes());
        investment.setInvestTypes(investTypes);

        return investment;
    }


    private List<InvestType> convertToInvestType(List<InvestTypeDTO> investTypeDTOs) {
        List<InvestType> investTypes = new ArrayList<>();
        for (InvestTypeDTO investTypeDTO : investTypeDTOs) {
            InvestType investType = new InvestType();
            investType.setInvestAmount(investTypeDTO.getInvestAmount());
            investType.setInvestLimit(investTypeDTO.getInvestLimit());
            investType.setInvestLimitCount(investTypeDTO.getInvestLimitCount());

            investTypes.add(investType);
        }
        return investTypes;
    }
    private InvestmentDTO convertToInvestDTO(Investment investment) {
        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setId(investment.getId());
        investmentDTO.setInvestProjName(investment.getInvestProjName());
        investmentDTO.setInvestTargetAmount(investment.getInvestTargetAmount());
        investmentDTO.setInvestProjDateStart(investment.getInvestProjDateStart());
        investmentDTO.setInvestProjDateEnd(investment.getInvestProjDateEnd());

        if (investment.getInvestBankAccountCopyImgSavedName () != null) {
            FileDTO ibacisnFileDTO = new FileDTO();
            ibacisnFileDTO.setFileName(investment.getInvestBankAccountCopyImgSavedName().getFileName());
            investmentDTO.setInvestBankAccountCopyImgSavedName(ibacisnFileDTO);
        }

        investmentDTO.setInvestProjKeyword(investment.getInvestProjKeyword());
        investmentDTO.setUseOfFunds(investment.getUseOfFunds());

        if (investment.getInvestContentImgSavedName() != null) {
            FileDTO iiblisnDTO  = new FileDTO();
            iiblisnDTO.setFileName(investment.getInvestContentImgSavedName().getFileName());
            investmentDTO.setInvestContentImgSavedName(iiblisnDTO);
        }

        investmentDTO.setUseOfFundsDateStart(investment.getUseOfFundsDateStart());
        investmentDTO.setUseOfFundsDateEnd(investment.getUseOfFundsDateEnd());
        investmentDTO.setRateOfReturn(investment.getRateOfReturn());
        investmentDTO.setExpectedPaymentDate(investment.getExpectedPaymentDate());
        investmentDTO.setRepaymentMethod(investment.getRepaymentMethod());
        investmentDTO.setInvestVideoUrl(investment.getInvestVideoUrl());
        investmentDTO.setInvestItemIntro(investment.getInvestItemIntro());
        investmentDTO.setInvestItemBusinessValue(investment.getInvestItemBusinessValue());
        investmentDTO.setInvestItemValue(investment.getInvestItemValue());
        investmentDTO.setInvestItemBenefit(investment.getInvestItemBenefit());
        investmentDTO.setInvestProjContent(investment.getInvestProjContent());

        if (investment.getInvestRepImgSavedName() != null) {
            FileDTO irisnDTO  = new FileDTO();
            irisnDTO .setFileName(investment.getInvestRepImgSavedName().getFileName());
            investmentDTO.setInvestRepImgSavedName(irisnDTO );
        }

        investmentDTO.setBusinessAddress(investment.getBusinessAddress());
        investmentDTO.setBank(investment.getBank());
        investmentDTO.setAccNumber(investment.getAccNumber());
        investmentDTO.setDepositorName(investment.getDepositorName());

        if (investment.getInvestContentImgSavedName() != null) {
            FileDTO ciisnDTO  = new FileDTO();
            ciisnDTO .setFileName(investment.getInvestContentImgSavedName().getFileName());
            investmentDTO.setInvestContentImgSavedName(ciisnDTO );
        }

        investmentDTO.setTaxBillEmail(investment.getTaxBillEmail());
        investmentDTO.setWebsiteUrl(investment.getWebsiteUrl());
        investmentDTO.setFacebookUrl(investment.getFacebookUrl());
        investmentDTO.setInstagramUrl(investment.getInstagramUrl());
        investmentDTO.setBlogUrl(investment.getBlogUrl());
        investmentDTO.setTwitterUrl(investment.getTwitterUrl());

        return investmentDTO;
    }


    public Map<String, Object> getInvestmentById(Long investmentId) {
        Map<String, Object> map = new HashMap<>();
        Optional<Investment> oInvestment = investmentRepository.findById(investmentId);
        if (oInvestment.isEmpty()) {
            throw new IllegalArgumentException("Reward not found with ID: " + investmentId);
        }
        Investment investment = oInvestment.get();
        map.put("investment", modelMapper.map(investment, InvestmentDTO.class));
        return map;
    }



    }

