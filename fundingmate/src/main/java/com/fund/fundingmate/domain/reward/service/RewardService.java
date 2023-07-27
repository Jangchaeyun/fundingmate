package com.fund.fundingmate.domain.reward.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fund.fundingmate.domain.investment.dto.InvestmentDTO;
import com.fund.fundingmate.domain.investment.entity.InvestType;
import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardOption;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardFindRepository;
import com.fund.fundingmate.domain.reward.repository.RewardOptionRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.reward.repository.RewardTypeRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.file.Repository.FileRepository;
import com.fund.fundingmate.global.file.Service.FileService;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RewardService {

    @Autowired
    private  RewardRepository rewardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RewardFindRepository rewardFindRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private RewardTypeRepository rewardTypeRepository;

    @Autowired
    private RewardOptionRepository rewardOptionRepository;

    @Autowired
    public RewardService(RewardRepository rewardRepository, UserRepository userRepository,
                         ModelMapper modelMapper, RewardFindRepository rewardFindRepository,
                         FileRepository fileRepository, RewardTypeRepository rewardTypeRepository, RewardOptionRepository rewardOptionRepository) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.rewardFindRepository = rewardFindRepository;
        this.fileRepository = fileRepository;
        this.rewardTypeRepository = rewardTypeRepository;
        this.rewardOptionRepository = rewardOptionRepository;
    }
    public Long createReward(RewardDTO rewardDTO, Long userId, String cards, MultipartFile reqFile, MultipartFile[] contentFiles, MultipartFile businessFile, MultipartFile bankFile) throws Exception {

        Reward reword = modelMapper.map(rewardDTO, Reward.class);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        reword.setUser(user);
        System.out.println(reword);
        // 파일 저장 시작
        String path = FileService.UPLOAD_DIRECTORY;

        if(reqFile!=null && !reqFile.isEmpty()) {
            File fileEntity = new File(reqFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            reqFile.transferTo(destFile);
            System.out.println(path+reqFile.getOriginalFilename());
            reword.setRewardRepImgSavedName(fileEntity.getFileId());
        }


        String fileIds = "";
        for(int i=0 ; i<contentFiles.length; i++) {
            MultipartFile file = contentFiles[i];
            if(file!=null && !file.isEmpty()) {
                File fileEntity = new File(file.getOriginalFilename());
                fileRepository.save(fileEntity);
                java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
                file.transferTo(destFile);
                fileIds += fileEntity.getFileId();
                if (i < contentFiles.length - 1) {
                    fileIds += ",";
                }
            }
        }
        reword.setRewardContentImgSavedName(fileIds);

        if(businessFile!=null && !businessFile.isEmpty()) {
            File fileEntity = new File(businessFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            businessFile.transferTo(destFile);
            reword.setRewardIdBusinessLicenseImgSavedName(fileEntity.getFileId());
        }
        if(bankFile!=null && !bankFile.isEmpty()) {
            File fileEntity = new File(bankFile.getOriginalFilename());
            fileRepository.save(fileEntity);
            java.io.File destFile = new java.io.File(path+"/"+fileEntity.getFileId());
            bankFile.transferTo(destFile);
            reword.setRewardBankAccountCopyImgSavedName(fileEntity.getFileId());
        }
        // 파일 저장 끝

        ObjectMapper objectMapper = new ObjectMapper();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray =  (JsonArray)parser.parse(cards);
        List<RewardType> rewardTypeList = new ArrayList<>();
        for(int i=0; i<jsonArray.size(); i++) {
            JsonObject rewordObject = (JsonObject)jsonArray.get(i);
            System.out.println(rewordObject.toString());
            RewardType rewardType = objectMapper.readValue(rewordObject.toString(), RewardType.class);
            for(RewardOption option : rewardType.getOptions()) {
                option.setRewardType(rewardType);
            }
            rewardType.setReward(reword);
            rewardTypeList.add(rewardType);
        }

        reword.setRewardTypes(rewardTypeList);
        rewardRepository.save(reword);
        return reword.getId();
    }
//    public Long createReward(RewardDTO rewardDTO, Long userId) {
//        System.out.println("reward" + rewardDTO);
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
//        Reward reward = convertToReward(rewardDTO);
//
//        File rewardBankAccountCopyImgSavedName = convertToFile(rewardDTO.getRewardBankAccountCopyImgSavedName());
//        reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
//        rewardBankAccountCopyImgSavedName = fileRepository.save(rewardBankAccountCopyImgSavedName);
//
//        File rewardIdBusinessLicenseImgSavedName = convertToFile(rewardDTO.getRewardIdBusinessLicenseImgSavedName());
//        reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
//        rewardIdBusinessLicenseImgSavedName = fileRepository.save(rewardIdBusinessLicenseImgSavedName);
//
//        File rewardRepImgSavedName = convertToFile(rewardDTO.getRewardRepImgSavedName());
//        reward.setRewardRepImgSavedName(rewardRepImgSavedName);
//        rewardRepImgSavedName = fileRepository.save(rewardRepImgSavedName);
//
//        List<FileDTO> rewardContentImgSavedNames = rewardDTO.getRewardContentImgSavedName();
//        List<File> rewardContentImgEntities = rewardContentImgSavedNames.stream()
//                .map(this::convertToFile)
//                .collect(Collectors.toList());
//
//        reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
//        reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
//        reward.setRewardRepImgSavedName(rewardRepImgSavedName);
//        reward.setRewardContentImgSavedName(rewardContentImgEntities);
//
//        reward.setUser(user);
//        for(RewardType rewardType : reward.getRewardTypes()) {
//            rewardType.setReward(reward);
//        }
//        System.out.println(reward);
//        Reward savedReward = rewardRepository.save(reward);
//
//        return savedReward.getId();
//    }


    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserid(userDTO.getUserid());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private FileDTO convertToFileDTO(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(file.getOriginalFilename());
        fileDTO.setFileRegistrationDate(new Date());

        return fileDTO;
    }

//    private List<File> convertToFileList(List<FileDTO> fileDTOs) {
//        if (fileDTOs == null) {
//            return Collections.emptyList();
//        }
//
//        return fileDTOs.stream()
//                .map(this::convertToFile)
//                .collect(Collectors.toList());
//    }

//    private File convertToFile(FileDTO fileDTO) {
//        if (fileDTO == null) {
//            return null;
//        }
//
//        File file = new File();
//        file.setFileId(fileDTO.getFileId());
//        file.setFileName(fileDTO.getFileName());
//        file.setFileRegistrationDate(new Date());
//
//        // Map the RewardDTO to the Reward entity
//        if (fileDTO.getReward() != null) {
//            Reward reward = convertToReward(fileDTO.getReward());
//            file.setReward(reward);
//        }
//        return file;
//    }


//    private Reward convertToReward(RewardDTO rewardDTO) {
//        Reward reward = new Reward();
//        reward.setRewardCategory(rewardDTO.getRewardCategory());
//        reward.setProjName(rewardDTO.getProjName());
//        reward.setProjTargetAmount(rewardDTO.getProjTargetAmount());
//        reward.setProjDateStart(rewardDTO.getProjDateStart());
//        reward.setProjDateEnd(rewardDTO.getProjDateEnd());
//
//        FileDTO bankFileDTO = rewardDTO.getRewardBankAccountCopyImgSavedName();
//        if (bankFileDTO != null) {
//            File rewardBankAccountCopyImgSavedName = convertToFile(bankFileDTO);
//            reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
//        }
//
//        reward.setProjKeyWord(rewardDTO.getProjKeyWord());
//        reward.setRewardVideoAddress(rewardDTO.getRewardVideoAddress());
//
//        FileDTO businessFileDTO = rewardDTO.getRewardIdBusinessLicenseImgSavedName();
//        if (businessFileDTO != null) {
//            File rewardIdBusinessLicenseImgSavedName = convertToFile(businessFileDTO);
//            reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
//        }
//
//        reward.setProjContent(rewardDTO.getProjContent());
//        reward.setRewardRefundExchangePolicy(rewardDTO.getRewardRefundExchangePolicy());
//        reward.setRewardContact(rewardDTO.getRewardContact());
//        reward.setRewardEmail(rewardDTO.getRewardEmail());
//        reward.setModelName(rewardDTO.getModelName());
//        reward.setCountryOfOrigin(rewardDTO.getCountryOfOrigin());
//        reward.setManufacturer(rewardDTO.getManufacturer());
//        reward.setRewardLaw(rewardDTO.getRewardLaw());
//        reward.setAsPhoneNumber(rewardDTO.getAsPhoneNumber());
//
//        FileDTO repFileDTO = rewardDTO.getRewardRepImgSavedName();
//        if (repFileDTO != null) {
//            File rewardRepImgSavedName = convertToFile(repFileDTO);
//            reward.setRewardRepImgSavedName(rewardRepImgSavedName);
//        }
//
//        reward.setBusinessAddress(rewardDTO.getBusinessAddress());
//        reward.setBank(rewardDTO.getBank());
//        reward.setAccNumber(rewardDTO.getAccNumber());
//        reward.setDepositorName(rewardDTO.getDepositorName());
//        reward.setTaxBillEmail(rewardDTO.getTaxBillEmail());
//
//        List<FileDTO> conFileDTOs = rewardDTO.getRewardContentImgSavedName();
//        List<File> rewardContentImgEntities = convertToFileList(conFileDTOs);
//        reward.setRewardContentImgSavedName(rewardContentImgEntities);
//
//        reward.setWebsiteUrl(rewardDTO.getWebsiteUrl());
//        reward.setFacebookUrl(rewardDTO.getFacebookUrl());
//        reward.setInstagramUrl(rewardDTO.getInstagramUrl());
//        reward.setBlogUrl(rewardDTO.getBlogUrl());
//        reward.setTwitterUrl(rewardDTO.getTwitterUrl());
//        UserDTO userDTO = rewardDTO.getUser();
//        if (userDTO != null) {
//            User user = convertToUser(userDTO);
//            reward.setUser(user);
//        }
//
//        List<RewardType> rewardTypes = convertToRewardType(rewardDTO.getRewardTypes());
//        reward.setRewardTypes(rewardTypes);
//
//        return reward;
//    }

    private List<RewardType> convertToRewardType(List<RewardTypeDTO> rewardTypeDTOs) {
        if (rewardTypeDTOs == null) {
            return Collections.emptyList();
        }

        List<RewardType> rewardTypes = new ArrayList<>();
        for (RewardTypeDTO rewardTypeDTO : rewardTypeDTOs) {
            if (rewardTypeDTO != null) {
                RewardType rewardType = convertToSingleRewardType(rewardTypeDTO);
                rewardTypes.add(rewardType);
            }
        }

        return rewardTypes;
    }

    private RewardType convertToSingleRewardType(RewardTypeDTO rewardTypeDTO) {
        RewardType rewardType = new RewardType();
        rewardType.setRewardAmount(rewardTypeDTO.getRewardAmount());
        rewardType.setRewardAvailableLimit(rewardTypeDTO.getRewardAvailableLimit());
        rewardType.setRewardAvailableCount(rewardTypeDTO.getRewardAvailableCount());
        rewardType.setRewardTitle(rewardTypeDTO.getRewardTitle());
        rewardType.setRewardContent(rewardTypeDTO.getRewardContent());
        rewardType.setRewardShipAddress(rewardTypeDTO.getRewardShipAddress());
        rewardType.setDeliveryDate(rewardTypeDTO.getDeliveryDate());

        return rewardType;
    }

//    private List<RewardOption> convertToRewardOption(List<RewardOptionDTO> rewardOptionDTOs) {
//        if (rewardOptionDTOs == null) {
//            return Collections.emptyList(); // Return an empty list if rewardOptionDTOs is null
//        }
//
//        List<RewardOption> rewardOptions = new ArrayList<>();
//        for (RewardOptionDTO rewardOptionDTO : rewardOptionDTOs) {
//            if (rewardOptionDTO != null) {
//                RewardOption rewardOption = new RewardOption();
//                rewardOption.setRewardOptName(rewardOptionDTO.getRewardOptName());
//                rewardOption.setGetRewardOptCon(rewardOptionDTO.getRewardOptCon());
//
//                List<RewardTypeDTO> rewardTypeDTOs = rewardOptionDTO.getRewardTypes();
//                List<RewardType> rewardTypes = convertToRewardType(rewardTypeDTOs);
//                rewardOption.setRewardTypes(rewardTypes);
//
//                rewardOptions.add(rewardOption);
//            }
//        }
//        return rewardOptions;
//    }






    public RewardDTO getRewardById(Long rewardId) {
        Optional<Reward> optionalReward = rewardRepository.findById(rewardId);
        if (optionalReward.isEmpty()) {
            throw new IllegalArgumentException("Reward not found with ID: " + rewardId);
        }
        Reward reward = optionalReward.get();
        return modelMapper.map(reward, RewardDTO.class);
    }

    public List<RewardDTO> getRewardWithProjDateEndBeforeToday() {
        List<Reward> rewards = rewardFindRepository.findRewardDatesAfter();

        return rewards.stream()
                .map(reward -> modelMapper.map(reward, RewardDTO.class))
                .collect(Collectors.toList());
    }

    public List<RewardDTO> getRewardWithProjDateEndBefore() {
        List<Reward> rewards = rewardFindRepository.findRewardDatesBefore();

        return rewards.stream()
                .map(reward -> modelMapper.map(reward, RewardDTO.class))
                .collect(Collectors.toList());
    }

    public List<RewardDTO> getRewardWithProjDateStartEndBetween() {
        List<Reward> rewards = rewardFindRepository.findRewardsBetweenDates();

        return rewards.stream()
                .map(reward -> modelMapper.map(reward, RewardDTO.class))
                .collect(Collectors.toList());
    }

    public List<RewardTypeDTO> getRewardTypesByRewardId(Long rewardId) {
        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward not found with ID: " + rewardId));
        List<RewardType> rewardTypes = reward.getRewardTypes();
        return rewardTypes.stream()
                .map(rewardType -> modelMapper.map(rewardType, RewardTypeDTO.class))
                .collect(Collectors.toList());
    }

    public RewardTypeDTO getRewardTypeById(Long rewardTypeId) {
        RewardType rewardType = rewardTypeRepository.findById(rewardTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Reward type not found with ID: " + rewardTypeId));
        return modelMapper.map(rewardType, RewardTypeDTO.class);
    }
}