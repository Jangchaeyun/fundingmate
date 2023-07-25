
package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardReplyDTO;
import com.fund.fundingmate.domain.reward.dto.RewardTypeDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardComment;
import com.fund.fundingmate.domain.reward.entity.RewardReply;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardCommentRepository;
import com.fund.fundingmate.domain.reward.repository.RewardReplyRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import com.fund.fundingmate.global.file.dto.FileDTO;
import com.fund.fundingmate.global.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RewardCommentService {
    private final RewardCommentRepository rewardCommentRepository;
    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final RewardReplyRepository rewardReplyRepository;

    private RewardCommentDTO rewardCommentDTO;

    @Autowired
    public RewardCommentService(RewardCommentRepository rewardCommentRepository, UserRepository userRepository, RewardRepository rewardRepository, RewardReplyRepository rewardReplyRepository) {
        this.rewardCommentRepository = rewardCommentRepository;
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
        this.rewardReplyRepository = rewardReplyRepository;
    }

    public RewardCommentDTO getRewardCommentByRewardId(Long rewardId) {
        if (rewardId == null) {
            throw new IllegalArgumentException("Reward ID must not be null");
        }

        RewardComment rewardComment = rewardCommentRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward Comment not found with ID: " + rewardId));

        return mapToRewardCommentDTO(rewardComment);
    }


    private RewardCommentDTO mapToRewardCommentDTO(RewardComment rewardComment) {
        RewardCommentDTO rewardCommentDTO = new RewardCommentDTO();
        rewardCommentDTO.setId(rewardComment.getId());
        rewardCommentDTO.setComContent(rewardComment.getComContent());
        rewardCommentDTO.setComRegistrationDate(rewardComment.getComRegistrationDate());
        rewardCommentDTO.setComRevisionDate(rewardComment.getComRevisionDate());
        rewardCommentDTO.setReward(mapToRewardDTO(rewardComment.getReward()));
        rewardCommentDTO.setUser(mapToUserDTO(rewardComment.getUser()));
        rewardCommentDTO.setReplies(mapToRewardReplyDTOList(rewardComment.getReplies()));

        return rewardCommentDTO;
    }

    public void updateRewardComment(Long commentId, String updatedContent) {
        RewardComment rewardComment = rewardCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Reward Comment not found with ID: " + commentId));

        rewardComment.setComContent(updatedContent);
        rewardComment.setComRevisionDate(LocalDate.now());

        rewardCommentRepository.save(rewardComment);
    }

    private RewardDTO mapToRewardDTO(Reward reward) {
        RewardDTO rewardDTO = new RewardDTO();
        rewardDTO.setId(reward.getId());
        rewardDTO.setProjName(reward.getProjName());
        rewardDTO.setProjTargetAmount(reward.getProjTargetAmount());
        rewardDTO.setProjDateStart(reward.getProjDateStart());
        rewardDTO.setProjDateEnd(reward.getProjDateEnd());
        rewardDTO.setProjKeyWord(reward.getProjKeyWord());
        rewardDTO.setRewardVideoAddress(reward.getRewardVideoAddress());
        rewardDTO.setProjContent(reward.getProjContent());
        rewardDTO.setRewardRefundExchangePolicy(reward.getRewardRefundExchangePolicy());
        rewardDTO.setRewardContact(reward.getRewardContact());
        rewardDTO.setRewardEmail(reward.getRewardEmail());
        rewardDTO.setRewardCategory(reward.getRewardCategory());
        rewardDTO.setModelName(reward.getModelName());
        rewardDTO.setCountryOfOrigin(reward.getCountryOfOrigin());
        rewardDTO.setManufacturer(reward.getManufacturer());
        rewardDTO.setRewardLaw(reward.getRewardLaw());
        rewardDTO.setAsPhoneNumber(reward.getAsPhoneNumber());
        rewardDTO.setBusinessAddress(reward.getBusinessAddress());
        rewardDTO.setBank(reward.getBank());
        rewardDTO.setAccNumber(reward.getAccNumber());
        rewardDTO.setDepositorName(reward.getDepositorName());
        rewardDTO.setTaxBillEmail(reward.getTaxBillEmail());
        rewardDTO.setWebsiteUrl(reward.getWebsiteUrl());
        rewardDTO.setFacebookUrl(reward.getFacebookUrl());
        rewardDTO.setInstagramUrl(reward.getInstagramUrl());
        rewardDTO.setBlogUrl(reward.getBlogUrl());
        rewardDTO.setTwitterUrl(reward.getTwitterUrl());
        rewardDTO.setUser(mapToUserDTO(reward.getUser()));
        rewardDTO.setRewardTypes(mapToRewardTypeDTOList(reward.getRewardTypes()));

//        rewardDTO.setRewardRepImgSavedName(mapToFileDTO(reward.getRewardRepImgSavedName()));
//        rewardDTO.setRewardContentImgSavedName(new ArrayList<>());
//        List<File> fileList = reward.getRewardContentImgSavedName();
//        for(File file : fileList) {
//            rewardDTO.getRewardContentImgSavedName().add(mapToFileDTO(file));
//        }
//        rewardDTO.setRewardIdBusinessLicenseImgSavedName(mapToFileDTO(reward.getRewardIdBusinessLicenseImgSavedName()));
//        rewardDTO.setRewardBankAccountCopyImgSavedName(mapToFileDTO(reward.getRewardBankAccountCopyImgSavedName()));

        return rewardDTO;
    }

    public List<RewardCommentDTO> getRewardCommentsByRewardId(Long rewardId) {
        List<RewardComment> rewardComments = rewardCommentRepository.findByRewardId(rewardId);

        return rewardComments.stream()
                .map(this::mapToRewardCommentDTO)
                .collect(Collectors.toList());
    }

    private List<FileDTO> mapToFileDTOList(List<File> files) {
        if (files == null) {
            return null;
        }

        return files.stream()
                .map(this::mapToFileDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserid(user.getUserid());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setTel(user.getTel());
        userDTO.setNotificationStatus(user.getNotificationStatus());
        userDTO.setVitalization(user.getVitalization());
        return userDTO;
    }

    private List<RewardReplyDTO> mapToRewardReplyDTOList(List<RewardReply> replies) {
        return replies.stream()
                .map(this::mapToRewardReplyDTO)
                .collect(Collectors.toList());
    }

    private FileDTO mapToFileDTO(File file) {
        if (file == null) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileId(file.getFileId());
        fileDTO.setFileName(file.getFileName());
        fileDTO.setFileRegistrationDate(file.getFileRegistrationDate());

        return fileDTO;
    }


    private RewardReplyDTO mapToRewardReplyDTO(RewardReply rewardReply) {
        RewardReplyDTO rewardReplyDTO = new RewardReplyDTO();
        rewardReplyDTO.setId(rewardReply.getId());
        rewardReplyDTO.setRepContent(rewardReply.getRepContent());
        rewardReplyDTO.setRepRegisterationDate(rewardReply.getRepRegistrationDate());
        rewardReplyDTO.setRepRevisionDate(rewardReply.getRepRevisionDate());
        rewardReplyDTO.setRewardId(rewardReply.getReward().getId());
        rewardReplyDTO.setCommentId(rewardReply.getComment().getId());
        rewardReplyDTO.setUserId(rewardReply.getUser().getId());
        return rewardReplyDTO;
    }

    public void insertRewardComment(RewardCommentDTO rewardCommentDTO) {
        if (rewardCommentDTO == null) {
            throw new IllegalArgumentException("RewardCommentDTO is null");
        }

        RewardDTO rewardDTO = rewardCommentDTO.getReward();
        UserDTO userDTO = rewardCommentDTO.getUser();


        if (rewardDTO == null) {
            throw new IllegalArgumentException("RewardDTO is null");
        }

        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO is null");
        }

        Long rewardId = rewardDTO.getId();
        Long userId = userDTO.getId();

        Optional<Reward> rewardOptional = rewardRepository.findById(rewardId);
        if (rewardOptional.isEmpty()) {
            throw new IllegalArgumentException("Reward not found with ID: " + rewardId);
        }
        Reward reward = rewardOptional.get();

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw  new IllegalArgumentException("User not founr with ID: " + userId);
        }

        User user = userOptional.get();

        RewardComment rewardComment = new RewardComment();
        rewardComment.setComContent(rewardCommentDTO.getComContent());
        rewardComment.setComRegistrationDate(LocalDate.now());
        rewardComment.setComRevisionDate(LocalDate.now());
        rewardComment.setReward(reward);
        rewardComment.setUser(user);

        rewardCommentRepository.save(rewardComment);
    }

    public void deleteRewardComment(Long commentId) {
        Optional<RewardComment> rewardCommentOptional = rewardCommentRepository.findById(commentId);
        if (rewardCommentOptional.isEmpty()) {
            throw new IllegalArgumentException("Reward Comment not found with ID: " + commentId);
        }
        RewardComment rewardComment = rewardCommentOptional.get();
        rewardCommentRepository.delete(rewardComment);
    }

    private List<RewardTypeDTO> mapToRewardTypeDTOList(List<RewardType> rewardTypes) {
        List<RewardTypeDTO> rewardTypeDTOList = new ArrayList<>();

        for (RewardType rewardType : rewardTypes) {
            RewardTypeDTO rewardTypeDTO = new RewardTypeDTO();
            rewardTypeDTO.setId(rewardType.getId());
            rewardTypeDTO.setRewardAmount(rewardType.getRewardAmount());
            rewardTypeDTO.setRewardAvailableLimit(rewardType.getRewardAvailableLimit());
            rewardTypeDTO.setRewardAvailableCount(rewardType.getRewardAvailableCount());
            rewardTypeDTO.setRewardTitle(rewardType.getRewardTitle());
            rewardTypeDTO.setRewardContent(rewardType.getRewardContent());
            rewardTypeDTO.setRewardShipAddress(rewardType.getRewardShipAddress());

            rewardTypeDTOList.add(rewardTypeDTO);
        }
        return rewardTypeDTOList;
    }

    public void insertRewardCommentReply(RewardReplyDTO rewardReplyDTO) {
        Long rewardId = rewardReplyDTO.getRewardId();
        Long commentId = rewardReplyDTO.getCommentId();
        Long userId = rewardReplyDTO.getUserId();

        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward not found with ID: " + rewardId));
        RewardComment comment = rewardCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Reward Comment not found with ID: " + commentId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        if(!reward.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("Only the creator of the Reward to reply to the comment.");
        }

        RewardReply rewardReply = new RewardReply();
        rewardReply.setRepContent(rewardReplyDTO.getRepContent());
        rewardReply.setRepRegistrationDate(LocalDate.now());
        rewardReply.setRepRevisionDate(LocalDate.now());
        rewardReply.setReward(reward);
        rewardReply.setComment(comment);
        rewardReply.setUser(user);

        rewardReplyRepository.save(rewardReply);
    }

    public List<RewardReplyDTO> getRewardCommentReplies(Long commentId) {
        RewardComment rewardComment = rewardCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Reward Comment not found with ID: " + commentId));

        return mapToRewardReplyDTOList(rewardComment.getReplies());
    }
}