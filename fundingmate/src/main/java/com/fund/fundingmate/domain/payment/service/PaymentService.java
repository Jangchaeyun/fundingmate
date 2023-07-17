package com.fund.fundingmate.domain.payment.service;

import com.fund.fundingmate.domain.payment.dto.PaymentDTO;
import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.payment.repository.PaymentRepository;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.fund.fundingmate.domain.reward.repository.RewardRepository;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final RewardRepository rewardRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository, RewardRepository rewardRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.rewardRepository = rewardRepository;
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

        payment.setUser(user);

        Long rewardId = paymentDTO.getReward().getId();
        if (rewardId != null) {
            Reward reward = rewardRepository.findById(rewardId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid reward ID"));
            payment.setReward(reward);
        }

        paymentRepository.save(payment);
    }

    public Integer getTotalPaymentAmount(List<Long> rewardIds) {
        return paymentRepository.getTotalPaymentAmountForRewards(rewardIds);
    }
}
