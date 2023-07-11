package com.fund.fundingmate.domain.payment.repository;

import com.fund.fundingmate.domain.payment.entity.Payment;
import com.fund.fundingmate.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(User user);
}
