package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardOptionDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardOption;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardFindRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.sql.Date;

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
    public RewardService(RewardRepository rewardRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void createReward(RewardDTO rewardDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Reward reward = convertToReward(rewardDTO);

        File bankImg = converToFile(rewardDTO.getBankImg());
        reward.setBankImg(bankImg);
        bankImg = fileRepository.save(bankImg);

        File businessImg = converToFile(rewardDTO.getBusinessImg());
        reward.setBusinessImg(businessImg);
        businessImg = fileRepository.save(businessImg);

        File repImg = converToFile(rewardDTO.getRepfile());
        reward.setRepfile(repImg);
        repImg = fileRepository.save(repImg);

        File conImg = converToFile(rewardDTO.getConfile());
        reward.setConfile(conImg);
        conImg = fileRepository.save(conImg);


        reward.setBankImg(bankImg);
        reward.setBusinessImg(businessImg);
        reward.setRepfile(repImg);
        reward.setConfile(conImg);

        reward.setUser(user);

        rewardRepository.save(reward);
    }

    public void createRewardWithUser(RewardDTO rewardDTO, UserDTO userDTO) {
        User user = convertToUser(userDTO);
        userRepository.save(user);

        Reward reward = convertToReward(rewardDTO);
        reward.setUser(user);

        rewardRepository.save(reward);
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
        file.setFileSavedName(fileDTO.getFileSavedName());
        file.setFileOriginalName(fileDTO.getFileOriginalName());
        file.setFilePath(fileDTO.getFilePath());
        file.setFileSize(fileDTO.getFileSize());
        file.setFileRegistrationDate(fileDTO.getFileRegistrationDate());
        return file;
    }

    private Reward convertToReward(RewardDTO rewardDTO) {
        Reward reward = new Reward();
        reward.setProjName(rewardDTO.getProjName());
        reward.setProjTargetAmout(rewardDTO.getProjTargetAmout());
        reward.setProjDateStart(rewardDTO.getProjDateStart());
        reward.setProjDateEnd(rewardDTO.getProjDateEnd());

        FileDTO repFileDTO = rewardDTO.getRepfile();
        if (repFileDTO != null) {
            File repFile = converToFile(repFileDTO);
            reward.setRepfile(repFile);
        }

        reward.setProjKeyWord(rewardDTO.getProjKeyWord());
        reward.setRewardVideoAddress(rewardDTO.getRewardVideoAddress());

        FileDTO confileDTO = rewardDTO.getConfile();
        if (confileDTO != null) {
            File confile = converToFile(confileDTO);
            reward.setConfile(confile);
        }

        reward.setProjContent(rewardDTO.getProjContent());
        reward.setRewardRefundExchangePolicy(rewardDTO.getRewardRefundExchangePolicy());
        reward.setRewardContact(rewardDTO.getRewardContact());
        reward.setRewardEmail(rewardDTO.getRewardEmail());
        reward.setRewardCategory(rewardDTO.getRewardCategory());
        reward.setModelName(rewardDTO.getModelName());
        reward.setCountryOfOrigin(rewardDTO.getCountryOfOrigin());
        reward.setManufacturer(rewardDTO.getManufacturer());
        reward.setRewardLaw(rewardDTO.getRewardLaw());
        reward.setAsPhoneNumber(rewardDTO.getAsPhoneNumber());

        FileDTO businessDTO = rewardDTO.getBusinessImg();
        if (businessDTO != null) {
            File business = converToFile(businessDTO);
            reward.setConfile(business);
        }

        reward.setBusinessAddress(rewardDTO.getBusinessAddress());
        reward.setBank(rewardDTO.getBank());
        reward.setAccNumber(rewardDTO.getAccNumber());
        reward.setDepositorName(rewardDTO.getDepositorName());

        FileDTO bankDTO = rewardDTO.getBankImg();
        if (bankDTO != null) {
            File bank = converToFile(bankDTO);
            reward.setConfile(bank);
        }

        reward.setTaxBillEmail(rewardDTO.getTaxBillEmail());
        reward.setWebsiteUrl(rewardDTO.getWebsiteUrl());
        reward.setFacebookUrl(rewardDTO.getFacebookUrl());
        reward.setInstagramUrl(rewardDTO.getInstagramUrl());
        reward.setBlogUrl(rewardDTO.getBlogUrl());
        reward.setTwitterUrl(rewardDTO.getTwitterUrl());
        reward.setUser(rewardDTO.getUser());

        List<RewardType> rewardTypes = convertToRewardType(rewardDTO.getRewardTypes());
        reward.setRewardTypes(rewardTypes);

        return reward;
    }

    private List<RewardType> convertToRewardType(List<RewardTypeDTO> rewardTypeDTOs) {
        List<RewardType> rewardTypes = new ArrayList<>();
        for (RewardTypeDTO rewardTypeDTO : rewardTypeDTOs) {
            RewardType rewardType = new RewardType();
            rewardType.setRewardAmount(rewardTypeDTO.getRewardAmount());
            rewardType.setRewardAvailableLimit(rewardTypeDTO.getRewardAvailableLimit());
            rewardType.setRewardAvailableCount(rewardTypeDTO.getRewardAvailableCount());
            rewardType.setRewardTitle(rewardTypeDTO.getRewardTitle());
            rewardType.setRewardContent(rewardTypeDTO.getRewardContent());
            rewardType.setRewardDeliveryDate(rewardTypeDTO.getRewardDeliveryDate());
            rewardType.setRewardShipAddress(rewardTypeDTO.getRewardShipAddress());

            RewardOptionDTO rewardOptionDTO = rewardTypeDTO.getRewardOption();
            RewardOption rewardOption = convertToRewardOption(rewardOptionDTO);
            rewardType.setRewardOption(rewardOption);

            rewardTypes.add(rewardType);
        }
        return rewardTypes;
    }


    private RewardOption convertToRewardOption(RewardOptionDTO rewardOptionDTO) {
        RewardOption rewardOption = new RewardOption();

        rewardOption.setRewardOptName(rewardOptionDTO.getRewardOptName());
        rewardOption.setGetRewardOptCon(rewardOptionDTO.getRewardOptCon());
        return rewardOption;
    }

    private RewardDTO convertToRewardDTO(Reward reward) {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setId(reward.getId());
        rewardDTO.setProjName(reward.getProjName());
        rewardDTO.setProjTargetAmout(reward.getProjTargetAmout());
        rewardDTO.setRewardLaw(reward.getRewardLaw());
        rewardDTO.setProjDateStart(reward.getProjDateStart());
        rewardDTO.setProjDateEnd(reward.getProjDateEnd());

        if (reward.getRepfile() != null) {
            FileDTO repFileDTO = new FileDTO();
            repFileDTO.setFileSavedName(reward.getRepfile().getFileSavedName());
            rewardDTO.setRepfile(repFileDTO);
        }

        rewardDTO.setProjKeyWord(reward.getProjKeyWord());
        rewardDTO.setRewardVideoAddress(reward.getRewardVideoAddress());

        if (reward.getConfile() != null) {
            FileDTO confileDTO = new FileDTO();
            confileDTO.setFileSavedName(reward.getConfile().getFileSavedName());
            rewardDTO.setConfile(confileDTO);
        }

        rewardDTO.setProjContent(reward.getProjContent());
        rewardDTO.setRewardRefundExchangePolicy(reward.getRewardRefundExchangePolicy());
        rewardDTO.setRewardContact(reward.getRewardContact());
        rewardDTO.setRewardEmail(reward.getRewardEmail());
        rewardDTO.setRewardCategory(reward.getRewardCategory());
        rewardDTO.setModelName(reward.getModelName());
        rewardDTO.setCountryOfOrigin(reward.getCountryOfOrigin());
        rewardDTO.setManufacturer(reward.getManufacturer());
        rewardDTO.setAsPhoneNumber(reward.getAsPhoneNumber());

        if (reward.getBusinessImg() != null) {
            FileDTO businessImgDTO = new FileDTO();
            businessImgDTO.setFileSavedName(reward.getBusinessImg().getFileSavedName());
            rewardDTO.setBusinessImg(businessImgDTO);
        }

        rewardDTO.setBusinessAddress(reward.getBusinessAddress());
        rewardDTO.setBank(reward.getBank());
        rewardDTO.setAccNumber(reward.getAccNumber());
        rewardDTO.setDepositorName(reward.getDepositorName());

        if (reward.getBankImg() != null) {
            FileDTO bankImgDTO = new FileDTO();
            bankImgDTO.setFileSavedName(reward.getBankImg().getFileSavedName());
            rewardDTO.setBankImg(bankImgDTO);
        }

        rewardDTO.setTaxBillEmail(reward.getTaxBillEmail());
        rewardDTO.setWebsiteUrl(reward.getWebsiteUrl());
        rewardDTO.setFacebookUrl(reward.getFacebookUrl());
        rewardDTO.setInstagramUrl(reward.getInstagramUrl());
        rewardDTO.setBlogUrl(reward.getBlogUrl());
        rewardDTO.setTwitterUrl(reward.getTwitterUrl());

        return rewardDTO;
    }


    public Map<String, Object> getRewardById(Long rewardJd) {
        Map<String, Object> map = new HashMap<>();
        Optional<Reward> oReward = rewardRepository.findById(rewardJd);
        if (oReward.isEmpty()) {
            throw new IllegalArgumentException("Reward not found with ID: " + rewardJd);
        }
        Reward reward = oReward.get();
        map.put("reward", modelMapper.map(reward, RewardDTO.class));
        return map;
    }

    public List<RewardDTO> getRewardWithProjDateEndBeforeToday() {
        List<Reward> rewards = rewardFindRepository.findRewardsBetweenDates();

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

    public List<RewardDTO> getRewardWithProjDateStartAfter() {
        List<Reward> rewards = rewardFindRepository.findRewardDatesAfter();

        return rewards.stream()
                .map(reward -> modelMapper.map(reward, RewardDTO.class))
                .collect(Collectors.toList());
    }

}
