package com.fund.fundingmate.domain.reward.repository;

import com.fund.fundingmate.domain.reward.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<Reward, Long> {
}
