package com.fund.fundingmate.domain.payment.repository;

import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(User user);

    @Query("SELECT coalesce(sum(p.paymentamount), 0) from Payment  p where p.id in (:rewardIds)")
    Integer getTotalPaymentAmountForRewards(@Param("rewardIds") List<Long> rewardIds);

    @Query("SELECT coalesce(sum(p.paymentamount), 0) from Payment p where p.rewardType.id = :rewardTypeId")
    Integer getTotalPaymentAmountForRewards(@Param("rewardTypeId") Long rewardTypeId);
}
