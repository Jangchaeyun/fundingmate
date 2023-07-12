package com.fund.fundingmate.domain.reward.repository;

import com.fund.fundingmate.domain.reward.entity.RewardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RewardCommentRepository extends JpaRepository<RewardComment, Long> {
    List<RewardComment> findByRewardId(Long rewardId);
}
