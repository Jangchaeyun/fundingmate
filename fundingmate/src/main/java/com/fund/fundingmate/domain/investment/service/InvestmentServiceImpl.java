package com.fund.fundingmate.domain.investment.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.InvestType;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.investment.repository.InvestmentRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.investment.dto.InvestTypeDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
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
   public Long createInvestment(Map<String, Object> param) throws  Exception {
        Long userId = Long.valueOf((String)param.get("userId"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        InvestmentDTO investmentDTO = new InvestmentDTO();
        investmentDTO.setInvestCategory((String)param.get("investCategory"));
        investmentDTO.setInvestTargetAmount(Integer.valueOf((String)param.get("investTargetAmount")));
        investmentDTO.setInvestProjName((String)param.get("investProjName"));
        investmentDTO.setInvestProjKeyword((String)param.get("investProjKeyword"));
        investmentDTO.setInvestProjDateStart(Date.valueOf((String)param.get("investProjDateStart")));
        investmentDTO.setInvestProjDateEnd(Date.valueOf((String)param.get("investProjDateEnd")));
        investmentDTO.setInvestVideoUrl((String)param.get("investVideoUrl"));
        investmentDTO.setInvestProjContent((String)param.get("investProjContent"));
        investmentDTO.setBusinessAddress((String)param.get("businessAddress"));
        investmentDTO.setBank((String)param.get("bank"));
        investmentDTO.setAccNumber((String)param.get("accNumber"));
        investmentDTO.setDepositorName((String)param.get("depositorName"));
        investmentDTO.setTaxBillEmail((String)param.get("taxBillEmail"));
        investmentDTO.setWebsiteUrl((String)param.get("websiteUrl"));
        investmentDTO.setFacebookUrl((String)param.get("facebookUrl"));
        investmentDTO.setInstagramUrl((String)param.get("instagramUrl"));
        investmentDTO.setBlogUrl((String)param.get("blogUrl"));
        investmentDTO.setTwitterUrl((String)param.get("twitterUrl"));
        investmentDTO.setUseOfFunds((String)param.get("useOfFunds"));
        investmentDTO.setUseOfFundsDateStart(Date.valueOf((String)param.get("useOfFundsDateStart")));
        investmentDTO.setUseOfFundsDateEnd(Date.valueOf((String)param.get("useOfFundsDateEnd")));
        investmentDTO.setRateOfReturn(Integer.valueOf((String)param.get("rateOfReturn")));
        investmentDTO.setExpectedPaymentDate(Date.valueOf((String)param.get("expectedPaymentDate")));
        investmentDTO.setRepaymentMethod((String)param.get("repaymentMethod"));
        investmentDTO.setInvestItemIntro((String)param.get("investItemIntro"));
        investmentDTO.setInvestItemBusinessValue((String)param.get("investItemBusinessValue"));
        investmentDTO.setInvestItemValue((String)param.get("investItemValue"));
        investmentDTO.setInvestItemBenefit((String)param.get("investItemBenefit"));
        investmentDTO.setInvestEmail((String)param.get("investEmail"));

        Investment investment = modelMapper.map(investmentDTO, Investment.class);
        investment.setUser(user);

        MultipartFile investRepImgSavedName = (MultipartFile)param.get("investRepImgSavedName");

//        String investContentImgSavedName = (String)param.get("investContentImgSavedName");
//        System.out.println(investContentImgSavedName);
        MultipartFile investIdBusinessLicenseImgSavedName = (MultipartFile)param.get("investIdBusinessLicenseImgSavedName");
        MultipartFile investBankAccountCopyImgSavedName = (MultipartFile)param.get("investBankAccountCopyImgSavedName");

        String path = FileService.UPLOAD_DIRECTORY;

        System.out.println(param.get("investRepImgSavedName"));
        if(investRepImgSavedName!=null && !investRepImgSavedName.isEmpty()) {
            java.io.File destFile = new java.io.File(path+investRepImgSavedName.getOriginalFilename());
            investRepImgSavedName.transferTo(destFile);
            File fileEntity = new File(investRepImgSavedName.getOriginalFilename());
            fileRepository.save(fileEntity);
            investment.setInvestRepImgSavedName(fileEntity.getFileId());
        } else {
            System.out.println("AAAAAAAA");
        }

        Integer investContentImgcnt = Integer.valueOf((String)param.get("investContentImgcnt"));
        System.out.println(investContentImgcnt);
        String fileIds = "";
        for(int i=0 ; i<investContentImgcnt; i++) {
            MultipartFile file = (MultipartFile)param.get("investContentImgSavedName"+i);
            if(file!=null && !file.isEmpty()) {
                java.io.File destFile = new java.io.File(path+file.getOriginalFilename());
                file.transferTo(destFile);
                File fileEntity = new File(file.getOriginalFilename());
                fileRepository.save(fileEntity);
                investment.setInvestRepImgSavedName(fileEntity.getFileId());
            }
        }
        investment.setInvestContentImgSavedName(fileIds);

//        if(investContentImgSavedName!=null && investContentImgSavedName.length!=0) {
//            String fileIds = "";
//            for(Object obj : investContentImgSavedName) {
//                MultipartFile file = (MultipartFile) obj;
//                java.io.File destFile = new java.io.File(path+file.getOriginalFilename());
//                file.transferTo(destFile);
//                File fileEntity = new File(file.getOriginalFilename());
//                fileRepository.save(fileEntity);
//                fileIds += fileEntity.getFileName()+",";
//            }
//            investment.setInvestContentImgSavedName(fileIds);
//        }

        if(investIdBusinessLicenseImgSavedName!=null && !investIdBusinessLicenseImgSavedName.isEmpty()) {
            java.io.File destFile = new java.io.File(path+investIdBusinessLicenseImgSavedName.getOriginalFilename());
            investIdBusinessLicenseImgSavedName.transferTo(destFile);
            File fileEntity = new File(investIdBusinessLicenseImgSavedName.getOriginalFilename());
            fileRepository.save(fileEntity);
            investment.setInvestIdBusinessLicenseImgSavedName(fileEntity.getFileId());
        }

        if(investBankAccountCopyImgSavedName!=null && !investBankAccountCopyImgSavedName.isEmpty()) {
            java.io.File destFile = new java.io.File(path+investBankAccountCopyImgSavedName.getOriginalFilename());
            investBankAccountCopyImgSavedName.transferTo(destFile);
            File fileEntity = new File(investBankAccountCopyImgSavedName.getOriginalFilename());
            fileRepository.save(fileEntity);
            investment.setInvestBankAccountCopyImgSavedName(fileEntity.getFileId());
        }

        System.out.println(param.get("cards"));
        JsonParser parser = new JsonParser();
        JsonArray jsonArray =  (JsonArray)parser.parse((String)param.get("cards"));
        ObjectMapper objectMapper = new ObjectMapper();
        for(int i=0; i<jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject)jsonArray.get(i);
            System.out.println(jsonObject);
            InvestType investType = new InvestType();
            investType.setInvestAmount(jsonObject.get("investAmount").getAsInt());
            investType.setInvestLimit(jsonObject.get("investLimit").getAsBoolean());
            investType.setInvestLimitCount(jsonObject.get("investLimitCount").getAsInt());
            investType.setInvestment(investment);
            System.out.println(investment.getInvestTypes());
            investment.getInvestTypes().add((InvestType) investType);
        }

        investmentRepository.save(investment);
        return investment.getId();
   }

//    @Override
//    public void createInvestWithUser(InvestmentDTO investmentDTO, UserDTO userDTO) {
//        User user = convertToUser(userDTO);
//        userRepository.save(user);
//
//        Investment investment = convertToInvestment(investmentDTO);
//        investment.setUser(user);
//
//        investmentRepository.save(investment);
//    }

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


//    private Investment convertToInvestment(InvestmentDTO investmentDTO) {
//        Investment investment = new Investment();
//        investment.setInvestCategory(investmentDTO.getInvestCategory());
//        investment.setInvestProjName(investmentDTO.getInvestProjName());
//        investment.setInvestTargetAmount(investmentDTO.getInvestTargetAmount());
//        investment.setInvestProjDateStart(investmentDTO.getInvestProjDateStart());
//        investment.setInvestProjDateEnd(investmentDTO.getInvestProjDateEnd());
//
//        FileDTO ibacisnFileDTO = investmentDTO.getInvestBankAccountCopyImgSavedName();
//        if (ibacisnFileDTO != null) {
//            File investBankAccountCopyImgSavedName = converToFile(ibacisnFileDTO);
//            investment.setInvestBankAccountCopyImgSavedName(investBankAccountCopyImgSavedName);
//        }
//
//        investment.setInvestProjKeyword(investmentDTO.getInvestProjKeyword());
//        investment.setUseOfFunds(investmentDTO.getUseOfFunds());
//
//        FileDTO iiblisnDTO = investmentDTO.getInvestIdBusinessLicenseImgSavedName();
//        if (iiblisnDTO != null) {
//            File investIdBusinessLicenseImgSavedName = converToFile(iiblisnDTO);
//            investment.setInvestIdBusinessLicenseImgSavedName(investIdBusinessLicenseImgSavedName);
//        }
//
//        investment.setUseOfFundsDateStart(investmentDTO.getUseOfFundsDateStart());
//        investment.setUseOfFundsDateEnd(investmentDTO.getUseOfFundsDateEnd());
//        investment.setRateOfReturn(investmentDTO.getRateOfReturn());
//        investment.setExpectedPaymentDate(investmentDTO.getExpectedPaymentDate());
//        investment.setRepaymentMethod(investmentDTO.getRepaymentMethod());
//        investment.setInvestVideoUrl(investmentDTO.getInvestVideoUrl());
//        investment.setInvestItemIntro(investmentDTO.getInvestItemIntro());
//        investment.setInvestItemBusinessValue(investmentDTO.getInvestItemBusinessValue());
//        investment.setInvestItemValue(investmentDTO.getInvestItemValue());
//        investment.setInvestItemBenefit(investmentDTO.getInvestItemBenefit());
//        investment.setInvestProjContent(investmentDTO.getInvestProjContent());
//
//        FileDTO irisnDTO = investmentDTO.getInvestRepImgSavedName();
//        if (irisnDTO != null) {
//            File investRepImgSavedName = converToFile(irisnDTO);
//            investment.setInvestRepImgSavedName(investRepImgSavedName);
//        }
//
//        investment.setBusinessAddress(investmentDTO.getBusinessAddress());
//        investment.setInvestEmail(investmentDTO.getInvestEmail());
//        investment.setBank(investmentDTO.getBank());
//        investment.setAccNumber(investmentDTO.getAccNumber());
//        investment.setDepositorName(investmentDTO.getDepositorName());
//
//        FileDTO ciisnDTO = investmentDTO.getInvestContentImgSavedName();
//        if (ciisnDTO != null) {
//            File investContentImgSavedName = converToFile(ciisnDTO);
//            investment.setInvestContentImgSavedName(investContentImgSavedName);
//        }
//
//        investment.setTaxBillEmail(investmentDTO.getTaxBillEmail());
//        investment.setWebsiteUrl(investmentDTO.getWebsiteUrl());
//        investment.setFacebookUrl(investmentDTO.getFacebookUrl());
//        investment.setInstagramUrl(investmentDTO.getInstagramUrl());
//        investment.setBlogUrl(investmentDTO.getBlogUrl());
//        investment.setTwitterUrl(investmentDTO.getTwitterUrl());
//        UserDTO userDTO = investmentDTO.getUser();
//        if (userDTO != null) {
//            User user = convertToUser(userDTO);
//            investment.setUser(user);
//        }
//        List<InvestType> investTypes = convertToInvestType(investmentDTO.getInvestTypes());
//        investment.setInvestTypes(investTypes);
//
//        return investment;
//    }


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
    /*private InvestmentDTO convertToInvestDTO(Investment investment) {
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

      *//*  if (investment.getInvestContentImgSavedName() != null) {
            FileDTO iiblisnDTO  = new FileDTO();
            iiblisnDTO.setFileName(investment.getInvestContentImgSavedName().getFileName());
            investmentDTO.setInvestContentImgSavedName(iiblisnDTO);
        }
*//*
        List<File> investContentImgSavedName = investment.getInvestContentImgSavedName();
        if (investContentImgSavedName != null && !investContentImgSavedName.isEmpty()) {
            List<FileDTO> iiblisnDTOList = new ArrayList<>();
            for (File file : investContentImgSavedName) {
                FileDTO iiblisnDTO = new FileDTO();
                iiblisnDTO.setFileName(file.getFileName());
                // 필요한 다른 파일 정보 설정

                iiblisnDTOList.add(iiblisnDTO);
            }
            investmentDTO.setInvestContentImgSavedName(iiblisnDTOList);
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
*/

    @Override
    public Long createInvestment(InvestmentDTO investmentDTO, Long userId, String cards, MultipartFile reqFile, MultipartFile[] contentFiles, MultipartFile businessFile, MultipartFile bankFile) throws Exception {

        Investment investment = modelMapper.map(investmentDTO, Investment.class);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        investment.setUser(user);
        System.out.println(investment);
        // 파일 저장 시작
        String path = FileService.UPLOAD_DIRECTORY;

        if(reqFile!=null && !reqFile.isEmpty()) {
            File fileEntity = new File(reqFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            reqFile.transferTo(destFile);
            System.out.println(path+reqFile.getOriginalFilename());
            investment.setInvestRepImgSavedName(fileEntity.getFileId());
        }


        String fileIds = "";
        for(int i=0 ; i<contentFiles.length; i++) {
            MultipartFile file = contentFiles[i];
            if(file!=null && !file.isEmpty()) {
                File fileEntity = new File(file.getOriginalFilename());
                fileRepository.save(fileEntity);
                java.io.File destFile = new java.io.File(path+"/"+fileEntity);
                file.transferTo(destFile);
                fileIds += fileEntity.getFileId()+",";
            }
        }
        investment.setInvestContentImgSavedName(fileIds);

        if(businessFile!=null && !businessFile.isEmpty()) {
            File fileEntity = new File(businessFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            businessFile.transferTo(destFile);
            investment.setInvestIdBusinessLicenseImgSavedName(fileEntity.getFileId());
        }
        if(bankFile!=null && !bankFile.isEmpty()) {
            File fileEntity = new File(bankFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            bankFile.transferTo(destFile);
            investment.setInvestBankAccountCopyImgSavedName(fileEntity.getFileId());
        }
        // 파일 저장 끝

        JsonParser parser = new JsonParser();
        JsonArray jsonArray =  (JsonArray)parser.parse(cards);
        for(int i=0; i<jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject)jsonArray.get(i);
            InvestType investType = new InvestType();
            investType.setInvestAmount(jsonObject.get("investAmount").getAsInt());
            investType.setInvestLimit(jsonObject.get("investLimit").getAsInt()==1);
            investType.setInvestLimitCount(jsonObject.get("investLimitCount").getAsInt());
            investType.setInvestment(investment);
            investment.getInvestTypes().add(investType);
        }

        investmentRepository.save(investment);
        return investment.getId();
    }

    /*   public Map<String, Object> getInvestmentById(Long investmentId) {
            Map<String, Object> map = new HashMap<>();
            Optional<Investment> oInvestment = investmentRepository.findById(investmentId);
            if (oInvestment.isEmpty()) {
                throw new IllegalArgumentException("Reward not found with ID: " + investmentId);
            }
            Investment investment = oInvestment.get();
            map.put("investment", modelMapper.map(investment, InvestmentDTO.class));
            return map;
        }*/
 public Map<String, Object> getInvestmentById(Long investmentId) {
     Map<String, Object> map = new HashMap<>();

     if (investmentId == null) {
         throw new IllegalArgumentException("Invalid investment ID: null");
     }

     Optional<Investment> oInvestment = investmentRepository.findById(investmentId);

     if (oInvestment.isEmpty()) {
         throw new IllegalArgumentException("Investment not found with ID: " + investmentId);
     }

     Investment investment = oInvestment.get();
     map.put("investment", modelMapper.map(investment, InvestmentDTO.class));

     return map;
 }
}

