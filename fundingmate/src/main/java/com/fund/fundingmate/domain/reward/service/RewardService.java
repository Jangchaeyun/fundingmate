package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
//import com.fund.fundingmate.domain.reward.dto.RewardOptionDTO;
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
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public Long createReward(RewardDTO rewardDTO, Long userId) {
        System.out.println("reward" + rewardDTO);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Reward reward = convertToReward(rewardDTO);

        File rewardBankAccountCopyImgSavedName = convertToFile(rewardDTO.getRewardBankAccountCopyImgSavedName());
        reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
        rewardBankAccountCopyImgSavedName = fileRepository.save(rewardBankAccountCopyImgSavedName);

        File rewardIdBusinessLicenseImgSavedName = convertToFile(rewardDTO.getRewardIdBusinessLicenseImgSavedName());
        reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
        rewardIdBusinessLicenseImgSavedName = fileRepository.save(rewardIdBusinessLicenseImgSavedName);

        File rewardRepImgSavedName = convertToFile(rewardDTO.getRewardRepImgSavedName());
        reward.setRewardRepImgSavedName(rewardRepImgSavedName);
        rewardRepImgSavedName = fileRepository.save(rewardRepImgSavedName);

        File rewardContentImgSavedName = convertToFile(rewardDTO.getRewardContentImgSavedName());
        reward.setRewardContentImgSavedName(rewardContentImgSavedName);
        rewardContentImgSavedName = fileRepository.save(rewardContentImgSavedName);

        reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
        reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
        reward.setRewardRepImgSavedName(rewardRepImgSavedName);
        reward.setRewardContentImgSavedName(rewardContentImgSavedName);

        reward.setUser(user);
        for(RewardType rewardType : reward.getRewardTypes()) {
            rewardType.setReward(reward);
        }
        System.out.println(reward);
        Reward savedReward = rewardRepository.save(reward);

        return savedReward.getId();
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUserid(userDTO.getUserid());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    private File convertToFile(FileDTO fileDTO) {
        if (fileDTO == null) {
            return null;
        }

        File file = new File();
        file.setFileId(fileDTO.getFileId());
        file.setFileName(fileDTO.getFileName());
        file.setFileRegistrationDate(new Date());

        // Map the RewardDTO to the Reward entity
        if (fileDTO.getReward() != null) {
            Reward reward = convertToReward(fileDTO.getReward());
            file.setReward(reward);
        }
        return file;
    }


    private Reward convertToReward(RewardDTO rewardDTO) {
        Reward reward = new Reward();
        reward.setRewardCategory(rewardDTO.getRewardCategory());
        reward.setProjName(rewardDTO.getProjName());
        reward.setProjTargetAmount(rewardDTO.getProjTargetAmount());
        reward.setProjDateStart(rewardDTO.getProjDateStart());
        reward.setProjDateEnd(rewardDTO.getProjDateEnd());

        FileDTO bankFileDTO = rewardDTO.getRewardBankAccountCopyImgSavedName();
        if (bankFileDTO != null) {
            File rewardBankAccountCopyImgSavedName = convertToFile(bankFileDTO);
            reward.setRewardBankAccountCopyImgSavedName(rewardBankAccountCopyImgSavedName);
        }

        reward.setProjKeyWord(rewardDTO.getProjKeyWord());
        reward.setRewardVideoAddress(rewardDTO.getRewardVideoAddress());

        FileDTO businessFileDTO = rewardDTO.getRewardIdBusinessLicenseImgSavedName();
        if (businessFileDTO != null) {
            File rewardIdBusinessLicenseImgSavedName = convertToFile(businessFileDTO);
            reward.setRewardIdBusinessLicenseImgSavedName(rewardIdBusinessLicenseImgSavedName);
        }

        reward.setProjContent(rewardDTO.getProjContent());
        reward.setRewardRefundExchangePolicy(rewardDTO.getRewardRefundExchangePolicy());
        reward.setRewardContact(rewardDTO.getRewardContact());
        reward.setRewardEmail(rewardDTO.getRewardEmail());
        reward.setModelName(rewardDTO.getModelName());
        reward.setCountryOfOrigin(rewardDTO.getCountryOfOrigin());
        reward.setManufacturer(rewardDTO.getManufacturer());
        reward.setRewardLaw(rewardDTO.getRewardLaw());
        reward.setAsPhoneNumber(rewardDTO.getAsPhoneNumber());

        FileDTO repFileDTO = rewardDTO.getRewardRepImgSavedName();
        if (repFileDTO != null) {
            File rewardRepImgSavedName = convertToFile(repFileDTO);
            reward.setRewardRepImgSavedName(rewardRepImgSavedName);
        }

        reward.setBusinessAddress(rewardDTO.getBusinessAddress());
        reward.setBank(rewardDTO.getBank());
        reward.setAccNumber(rewardDTO.getAccNumber());
        reward.setDepositorName(rewardDTO.getDepositorName());
        reward.setTaxBillEmail(rewardDTO.getTaxBillEmail());

        FileDTO conFileDTO = rewardDTO.getRewardContentImgSavedName();
        if (conFileDTO != null) {
            File rewardContentImgSavedName = convertToFile(conFileDTO);
            reward.setRewardContentImgSavedName(rewardContentImgSavedName);
        }

        reward.setWebsiteUrl(rewardDTO.getWebsiteUrl());
        reward.setFacebookUrl(rewardDTO.getFacebookUrl());
        reward.setInstagramUrl(rewardDTO.getInstagramUrl());
        reward.setBlogUrl(rewardDTO.getBlogUrl());
        reward.setTwitterUrl(rewardDTO.getTwitterUrl());
        UserDTO userDTO = rewardDTO.getUser();
        if (userDTO != null) {
            User user = convertToUser(userDTO);
            reward.setUser(user);
        }

        List<RewardType> rewardTypes = convertToRewardType(rewardDTO.getRewardTypes());
        reward.setRewardTypes(rewardTypes);

        return reward;
    }

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