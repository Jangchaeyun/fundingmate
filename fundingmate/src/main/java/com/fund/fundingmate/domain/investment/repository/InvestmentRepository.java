package com.fund.fundingmate.domain.investment.repository;

import com.fund.fundingmate.domain.investment.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
}
