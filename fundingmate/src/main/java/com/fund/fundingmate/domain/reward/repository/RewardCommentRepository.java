package com.fund.fundingmate.domain.reward.repository;

import com.fund.fundingmate.domain.reward.entity.RewardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardCommentRepository extends JpaRepository<RewardComment, Long> {
}
