package com.fund.fundingmate.domain.payment.service;

import com.fund.fundingmate.domain.payment.dto.PaymentDTO;
import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.payment.repository.PaymentRepository;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.entity.RewardType;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.reward.repository.RewardTypeRepository;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;

    private final RewardTypeRepository rewardTypeRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, RewardRepository rewardRepository, RewardTypeRepository rewardTypeRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
        this.rewardTypeRepository = rewardTypeRepository;
    }

    public void createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        payment.setId(paymentDTO.getId());
        payment.setCardnumber(paymentDTO.getCardnumber());
        payment.setCardpassword(paymentDTO.getCardpassword());
        payment.setPaymentamount(paymentDTO.getPaymentamount());
        payment.setPayenddate(paymentDTO.getPayenddate());
        payment.setBirthday(paymentDTO.getBirthday());
        payment.setPayperiod(paymentDTO.getPayperiod());
        payment.setShippingadress(paymentDTO.getShippingadress());
        payment.setShippingaddressdesc(paymentDTO.getShippingaddressdesc());

        User user = userRepository.findById(paymentDTO.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        RewardType rewardType = rewardTypeRepository.findById(paymentDTO.getRewardType().getId())
                .orElseThrow(() -> new EntityNotFoundException("RewardType not found with ID: " + paymentDTO.getRewardType().getId()));
        payment.setRewardType(rewardType);

        payment.setUser(user);

        paymentRepository.save(payment);
    }

   public Map<Long, Integer> getTotalPaymentAmountsForSameRewards(List<Long> rewardIds) {
        Map<Long, Integer> totalAmounts = new HashMap<>();
        for (Long rewardId: rewardIds) {
            Integer totalAmount = paymentRepository.getTotalPaymentAmountForRewards(Collections.singletonList(rewardId));
            totalAmounts.put(rewardId, totalAmount);
        }
        return totalAmounts;
   }

   public Map<Long, Integer> getTotalPaymentAmountsForSameInvests(List<Long> investIds) {
        Map<Long, Integer> totalAmounts = new HashMap<>();
        for (Long investId : investIds) {
            Integer totalAmount = paymentRepository.getTotalPaymentAmountForInvestments(Collections.singletonList(investId));
            totalAmounts.put(investId, totalAmount);
        }
        return totalAmounts;
   }

    public Integer countDistinctUserIds() {
        return paymentRepository.countDistinctUserIds();
    }

    public Integer countDistinctUserIdsForReward(Long rewardId) {
        return paymentRepository.countDistinctUserIdsForReward(rewardId);
    }

    public Integer countDistinctUserIdsForInvest(Long investId) {
        return paymentRepository.countDistinctUserIdsForInvest(investId);
    }
}
