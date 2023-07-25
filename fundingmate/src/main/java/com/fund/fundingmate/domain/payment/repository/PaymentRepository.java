package com.fund.fundingmate.domain.payment.repository;

import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.user.entity.User;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("SELECT coalesce(sum(p.paymentamount), 0) FROM Payment p WHERE p.rewardType.reward.id IN :rewardIds")
    Integer getTotalPaymentAmountForRewards(@Param("rewardIds") List<Long> rewardIds);

    @Query("SELECT coalesce(sum(p.paymentamount), 0) FROM Payment p WHERE p.investType.investment.id IN :investIds")
    Integer getTotalPaymentAmountForInvestments(@Param("investIds") List<Long> investIds);

    // Count distinct users for each rewarded ID
    @Query("SELECT p.rewardType.reward.id, COUNT(DISTINCT p.user.id) FROM Payment p GROUP BY p.rewardType.reward.id")
    Map<Long, Integer> countDistinctUserIdsPerReward();

    // Count total number of distinct users across all rewards
    @Query("SELECT COUNT(DISTINCT p.user.id) FROM Payment p")
    Integer countDistinctUserIds();

    @Query("SELECT COUNT(DISTINCT p.user.id) FROM Payment p WHERE p.rewardType.reward.id = :rewardId")
    Integer countDistinctUserIdsForReward(@Param("rewardId") Long rewardId);

    @Query("SELECT COUNT (DISTINCT p.user.id) FROM Payment p WHERE p.investType.investment.id = :investId")
    Integer countDistinctUserIdsForInvest(@Param("investId") Long investId);
}
