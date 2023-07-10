package com.fund.fundingmate.domain.reward.service;

import com.fund.fundingmate.domain.reward.dto.RewardCommentDTO;
import com.fund.fundingmate.domain.reward.dto.RewardDTO;
import com.fund.fundingmate.domain.reward.dto.RewardReplyDTO;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardComment;
import com.fund.fundingmate.domain.reward.entity.RewardReply;
import com.fund.fundingmate.domain.reward.repository.RewardCommentRepository;
import com.fund.fundingmate.domain.reward.repository.RewardReplyRepository;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.user.dto.UserDTO;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class RewardCommentService {
    private final RewardCommentRepository rewardCommentRepository;
    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;
    private final RewardReplyRepository rewardReplyRepository;

    @Autowired
    public RewardCommentService(RewardCommentRepository rewardCommentRepository, UserRepository userRepository, RewardRepository rewardRepository, RewardReplyRepository rewardReplyRepository) {
        this.rewardCommentRepository = rewardCommentRepository;
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
        this.rewardReplyRepository = rewardReplyRepository;
    }

    public RewardCommentDTO getRewardCommentByRewardId(Long rewardId) {
        RewardComment rewardComment = rewardCommentRepository.findByRewardId(rewardId);

        if (rewardComment == null) {
            return null;
        }

        RewardCommentDTO rewardCommentDTO = new RewardCommentDTO();
        rewardCommentDTO.setId(rewardComment.getId());
        rewardCommentDTO.setComContent(rewardComment.getComContent());
        rewardCommentDTO.setComRegistrationDate(rewardComment.getComRegistrationDate());
        rewardCommentDTO.setComRevisionDate(rewardComment.getComRevisionDate());

        return rewardCommentDTO;
    }

    public void insertRewardComment(RewardCommentDTO rewardCommentDTO) {
        RewardDTO rewardDTO = rewardCommentDTO.getReward();
        UserDTO userDTO = rewardCommentDTO.getUser();

        Reward reward = rewardRepository.findById(rewardDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Reward not found with ID: " + rewardDTO.getId()));

        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userDTO.getId()));


        RewardComment rewardComment = new RewardComment();
        rewardComment.setComContent(rewardCommentDTO.getComContent());
        rewardComment.setComRegistrationDate(new Date());
        rewardComment.setComRevisionDate(new Date());
        rewardComment.setReward(reward);
        rewardComment.setUser(user);

        rewardCommentRepository.save(rewardComment);
    }

    public void insertRewardCommentReply(RewardReplyDTO rewardReplyDTO) {
        Long rewardId = rewardReplyDTO.getRewardId();
        Long commentId = rewardReplyDTO.getCommentId();

        Reward reward = rewardRepository.findById(rewardId)
                .orElseThrow(() -> new IllegalArgumentException("Reward not found with ID: " + rewardId));
        RewardComment  comment = rewardCommentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Reward Comment not found with ID: " + commentId));

        if(!reward.getUser().getId().equals(comment.getUser().getId())) {
            throw new IllegalStateException("Only the creator of the Reward to reply to the comment.");
        }

        RewardReply rewardReply = new RewardReply();
        rewardReply.setRepContent(rewardReplyDTO.getRepContent());
        rewardReply.setRepRegistrationDate(new Date());
        rewardReply.setRepRevisionDate(new Date());
        rewardReply.setReward(reward);
        rewardReply.setComment(comment);

        rewardReplyRepository.save(rewardReply);
    }
}
