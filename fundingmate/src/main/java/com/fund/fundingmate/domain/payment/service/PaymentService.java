package com.fund.fundingmate.domain.payment.service;

import com.fund.fundingmate.domain.payment.dto.PaymentDTO;
import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.payment.repository.PaymentRepository;
import com.fund.fundingmate.domain.user.entity.User;
import com.fund.fundingmate.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, UserRepository userRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
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

        paymentRepository.save(payment);
    }
}
