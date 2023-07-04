package com.fund.fundingmate.domain.payment.repository;

import com.fund.fundingmate.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
