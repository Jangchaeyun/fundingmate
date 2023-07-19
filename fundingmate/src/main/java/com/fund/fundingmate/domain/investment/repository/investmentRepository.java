package com.fund.fundingmate.domain.investment.repository;

import com.fund.fundingmate.domain.investment.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface investmentRepository extends JpaRepository<Investment, Long> {
}
