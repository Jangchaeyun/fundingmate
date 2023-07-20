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
    public RewardService(RewardRepository rewardRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.rewardRepository = rewardRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public Long createReward(RewardDTO rewardDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        Reward reward = convertToReward(rewardDTO);

        File bankImg = converToFile(rewardDTO.getBankImg());
        reward.setBankImg(bankImg);
        bankImg = fileRepository.save(bankImg);

        File businessImg = converToFile(rewardDTO.getBusinessImg());
        reward.setBusinessImg(businessImg);
        businessImg = fileRepository.save(businessImg);

        File repImg = converToFile(rewardDTO.getRepFile());
        reward.setRepfile(repImg);
        repImg = fileRepository.save(repImg);

        File conImg = converToFile(rewardDTO.getConFile());
        reward.setConfile(conImg);
        conImg = fileRepository.save(conImg);


        reward.setBankImg(bankImg);
        reward.setBusinessImg(businessImg);
        reward.setRepfile(repImg);
        reward.setConfile(conImg);

        reward.setUser(user);

        rewardRepository.save(reward);

        return reward.getId();
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
        file.setFileName(fileDTO.getFileName());
        file.setFileRegistrationDate(fileDTO.getFileRegistrationDate());
        return file;
    }

    private Reward convertToReward(RewardDTO rewardDTO) {
        Reward reward = new Reward();
        reward.setProjName(rewardDTO.getProjName());
        reward.setProjTargetAmount(rewardDTO.getProjTargetAmount());
        reward.setProjDateStart(rewardDTO.getProjDateStart());
        reward.setProjDateEnd(rewardDTO.getProjDateEnd());
        reward.setDeliveryDate(rewardDTO.getDeliveryDate());

        FileDTO repFileDTO = rewardDTO.getRepFile();
        if (repFileDTO != null) {
            File repFile = converToFile(repFileDTO);
            reward.setRepfile(repFile);
        }

        reward.setProjKeyWord(rewardDTO.getProjKeyWord());
        reward.setRewardVideoAddress(rewardDTO.getRewardVideoAddress());

        FileDTO confileDTO = rewardDTO.getConFile();
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
        List<RewardType> rewardTypes = new ArrayList<>();
        for (RewardTypeDTO rewardTypeDTO : rewardTypeDTOs) {
            RewardType rewardType = new RewardType();
            rewardType.setRewardAmount(rewardTypeDTO.getRewardAmount());
            rewardType.setRewardAvailableLimit(rewardTypeDTO.getRewardAvailableLimit());
            rewardType.setRewardAvailableCount(rewardTypeDTO.getRewardAvailableCount());
            rewardType.setRewardTitle(rewardTypeDTO.getRewardTitle());
            rewardType.setRewardContent(rewardTypeDTO.getRewardContent());
            rewardType.setRewardShipAddress(rewardTypeDTO.getRewardShipAddress());

            List<RewardOption> rewardOptions = convertToRewardOptions(rewardTypeDTO.getRewardOption());
            rewardType.setRewardOptions(rewardOptions);

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

    private List<RewardOption> convertToRewardOptions(RewardOptionDTO rewardOptionDTO) {
        return Collections.singletonList(convertToRewardOption(rewardOptionDTO));
    }


    private RewardDTO convertToRewardDTO(Reward reward) {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setId(reward.getId());
        rewardDTO.setProjName(reward.getProjName());
        rewardDTO.setProjTargetAmount(reward.getProjTargetAmount());
        rewardDTO.setRewardLaw(reward.getRewardLaw());
        rewardDTO.setProjDateStart(reward.getProjDateStart());
        rewardDTO.setProjDateEnd(reward.getProjDateEnd());

        if (reward.getRepfile() != null) {
            FileDTO repFileDTO = new FileDTO();
            repFileDTO.setFileName(reward.getRepfile().getFileName());
            rewardDTO.setRepFile(repFileDTO);
        }

        rewardDTO.setProjKeyWord(reward.getProjKeyWord());
        rewardDTO.setRewardVideoAddress(reward.getRewardVideoAddress());

        if (reward.getConfile() != null) {
            FileDTO confileDTO = new FileDTO();
            confileDTO.setFileName(reward.getConfile().getFileName());
            rewardDTO.setConFile(confileDTO);
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
            businessImgDTO.setFileName(reward.getBusinessImg().getFileName());
            rewardDTO.setBusinessImg(businessImgDTO);
        }

        rewardDTO.setBusinessAddress(reward.getBusinessAddress());
        rewardDTO.setBank(reward.getBank());
        rewardDTO.setAccNumber(reward.getAccNumber());
        rewardDTO.setDepositorName(reward.getDepositorName());

        if (reward.getBankImg() != null) {
            FileDTO bankImgDTO = new FileDTO();
            bankImgDTO.setFileName(reward.getBankImg().getFileName());
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
        Optional<Reward> optionalReward = rewardRepository.findById(rewardId);
        if (optionalReward.isEmpty()) {
            throw new IllegalArgumentException("Reward not found with ID: " + rewardId);
        }
        Reward reward = optionalReward.get();
        List<RewardType> rewardTypes = reward.getRewardTypes();
        return rewardTypes.stream()
                .map(rewardType -> modelMapper.map(rewardType, RewardTypeDTO.class))
                .collect(Collectors.toList());
    }

    public RewardTypeDTO getRewardTypeById(Long rewardTypeId) {
        Optional<RewardType> optionalRewardType = rewardTypeRepository.findById(rewardTypeId);
        if (optionalRewardType.isEmpty()) {
            throw new IllegalArgumentException("Reward type not found with ID: " + rewardTypeId);
        }
        return modelMapper.map(optionalRewardType.get(), RewardTypeDTO.class);
    }
}