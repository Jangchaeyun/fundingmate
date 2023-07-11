package com.fund.fundingmate.domain.investment.repository;

import com.fund.fundingmate.domain.investment.entity.Investment;
import com.fund.fundingmate.domain.user.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InvestmentDslRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public void insertMakeInvest(Investment investment){
        /*QUser user = QUser.user;
        */
    }
}
