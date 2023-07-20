package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardOptionDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardOption;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardFindRepository;
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
    private RewardTypeRepository rewardTypeRepository;

    @Autowired
    public RewardService(RewardRepository rewardRepository, UserRepository userRepository,
                         ModelMapper modelMapper, RewardFindRepository rewardFindRepository,
                         FileRepository fileRepository, RewardTypeRepository rewardTypeRepository) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.rewardFindRepository = rewardFindRepository;
        this.fileRepository = fileRepository;
        this.rewardTypeRepository = rewardTypeRepository;
    }

    public Long createReward(RewardDTO rewardDTO, Long userId) {

    }

    private File convertToFile(FileDTO fileDTO) {
        return modelMapper.map(fileDTO, File.class);
    }

    private Reward convertToReward(RewardDTO rewardDTO) {
    }


    private List<RewardType> convertToRewardTypes(List<RewardTypeDTO> rewardTypeDTOs) {
        if (rewardTypeDTOs == null) {
            return new ArrayList<>();
        }

        return rewardTypeDTOs.stream()
                .map(this::convertToRewardType)
                .collect(Collectors.toList());
    }

    private RewardType convertToRewardType(RewardTypeDTO rewardTypeDTO) {

    }


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