package com.fund.fundingmate.domain.reward.repository;

import com.fund.fundingmate.domain.reward.entity.RewardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RewardCommentRepository extends JpaRepository<RewardComment, Long> {
    RewardComment findByRewardId(Long rewardId);
}
