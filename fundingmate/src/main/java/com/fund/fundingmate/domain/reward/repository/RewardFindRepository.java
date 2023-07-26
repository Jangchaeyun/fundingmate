package com.fund.fundingmate.domain.reward.repository;

import com.fund.fundingmate.domain.payment.entity.QPayment;
import com.fund.fundingmate.domain.reward.entity.QReward;
import com.fund.fundingmate.domain.reward.entity.QRewardType;
import com.fund.fundingmate.domain.reward.entity.Reward;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class RewardFindRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<Reward> findRewardsBetweenDates() {
        QReward reward = QReward.reward;
        LocalDate currentDate = LocalDate.now();
        return jpaQueryFactory.selectFrom(reward)
                .where(reward.projDateStart.loe(Date.valueOf(currentDate))
                        .and(reward.projDateEnd.after(Date.valueOf(currentDate))
                                .or(reward.projDateEnd.eq(Date.valueOf(currentDate)))))
                .fetch();



    }

    public List<Reward> findRewardDatesBefore() {
        QReward reward = QReward.reward;
        LocalDate currentDate = LocalDate.now();
        return jpaQueryFactory.selectFrom(reward)
                .where(reward.projDateEnd.before(Date.valueOf(currentDate)))
                .fetch();
    }

    public List<Reward> findRewardDatesAfter() {
        QReward reward = QReward.reward;
        LocalDate currentDate = LocalDate.now();
        return jpaQueryFactory.selectFrom(reward)
                .where(reward.projDateStart.after(Date.valueOf(currentDate)))
                .fetch();
    }



}
