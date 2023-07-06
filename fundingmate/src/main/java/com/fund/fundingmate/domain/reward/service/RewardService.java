package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardOptionDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardOption;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RewardService {
    private final RewardRepository rewardRepository;
    private final UserRepository userRepository;

    @Autowired
    public RewardService(RewardRepository rewardRepository, UserRepository userRepository) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
    }

    public void createReward(RewardDTO rewardDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Reward reward = convertToReward(rewardDTO);
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

    private Reward convertToReward(RewardDTO rewardDTO) {
        Reward reward = new Reward();
        reward.setProjName(rewardDTO.getProjName());
        reward.setProjTargetAmout(rewardDTO.getProjTargetAmout());
        rewardDTO.setProjDateStart(LocalDate.of(2023, 7, 1));
        rewardDTO.setProjDateEnd(LocalDate.of(2023, 7, 31));
        reward.setRewardRepImgSavedName(rewardDTO.getRewardRepImgSavedName());
        reward.setProjKeyWord(rewardDTO.getProjKeyWord());
        reward.setRewardVideoAddress(rewardDTO.getRewardVideoAddress());
        reward.setRewardContentImgSavedName(rewardDTO.getRewardContentImgSavedName());
        reward.setProjContent(rewardDTO.getProjContent());
        reward.setRewardRefundExchangePolicy(rewardDTO.getRewardRefundExchangePolicy());
        reward.setRewardContact(rewardDTO.getRewardContact());
        reward.setRewardEmail(rewardDTO.getRewardEmail());
        reward.setRewardCategory(rewardDTO.getRewardCategory());
        reward.setModelName(rewardDTO.getModelName());
        reward.setCountryOfOrigin(rewardDTO.getCountryOfOrigin());
        reward.setManufacturer(rewardDTO.getManufacturer());
        reward.setAsPhoneNumber(rewardDTO.getAsPhoneNumber());
        reward.setRewardIdBusinessLicenseImgSavedName(rewardDTO.getRewardIdBusinessLicenseImgSavedName());
        reward.setBusinessAddress(rewardDTO.getBusinessAddress());
        reward.setBank(rewardDTO.getBank());
        reward.setAccNumber(rewardDTO.getAccNumber());
        reward.setDepositorName(rewardDTO.getDepositorName());
        reward.setRewardBankAccountCopyImgSavedName(rewardDTO.getRewardBankAccountCopyImgSavedName());
        reward.setTaxBillEmail(rewardDTO.getTaxBillEmail());
        reward.setWebsiteUrl(rewardDTO.getWebsiteUrl());
        reward.setFacebookUrl(rewardDTO.getFacebookUrl());
        reward.setInstagramUrl(rewardDTO.getInstagramUrl());
        reward.setBlogUrl(rewardDTO.getBlogUrl());
        reward.setTwitterUrl(rewardDTO.getTwitterUrl());

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
}
