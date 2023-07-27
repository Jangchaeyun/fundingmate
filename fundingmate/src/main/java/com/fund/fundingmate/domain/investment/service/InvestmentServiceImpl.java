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

//    @Autowired
//    private FileService fileService;



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
                java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
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

