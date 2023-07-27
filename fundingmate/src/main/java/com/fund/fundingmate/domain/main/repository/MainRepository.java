package com.fund.fundingmate.domain.main.repository;

import com.fund.fundingmate.domain.investment.entity.QInvestType;
import com.fund.fundingmate.domain.investment.entity.QInvestment;
import com.fund.fundingmate.domain.payment.entity.QPayment;
import com.fund.fundingmate.domain.reward.entity.QReward;
import com.fund.fundingmate.domain.reward.entity.QRewardType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class MainRepository {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    public List<Tuple> findRewardAll(String word) {
        QReward reward = QReward.reward;
        QRewardType rewardType = QRewardType.rewardType;
        QPayment payment = QPayment.payment;
        LocalDate today = LocalDate.now();

        BooleanExpression whereCondition = reward.projDateStart.loe(Date.valueOf(today))
                .and(reward.projDateEnd.goe(Date.valueOf(today)));
        if (word != null && !word.isEmpty()) {
            whereCondition = whereCondition.and(
                    reward.projName.contains(word)
                            .or(reward.projKeyWord.contains(word))
            );
        }
        return jpaQueryFactory
                .select(
                        reward,
                        ExpressionUtils.as(JPAExpressions.select(payment.paymentamount.sum())
                                .from(rewardType)
                                .join(payment)
                                .on(rewardType.id.eq(payment.rewardType.id))
                                .where(reward.id.eq(rewardType.reward.id))
                                .groupBy(rewardType.reward.id),"paymentamountSum")
                )
                .from(reward)
                .where(whereCondition)
                .groupBy(reward.id)
                .orderBy(reward.id.desc())
                .limit(10)
                .fetch();
    }
    public List<Tuple> findInvestAll(String word) {
        QInvestment investment = QInvestment.investment;
        QInvestType investType = QInvestType.investType;
        QPayment payment = QPayment.payment;
        LocalDate today = LocalDate.now();
        BooleanExpression whereCondition = investment.investProjDateStart.loe(Date.valueOf(today))
                .and(investment.investProjDateEnd.goe(Date.valueOf(today)));

        if (word != null && !word.isEmpty()) {
            whereCondition = whereCondition.and(
                    investment.investProjName.contains(word)
                            .or(investment.investProjKeyword.contains(word))
            );
        }
        return jpaQueryFactory
                .select(
                        investment,
                        ExpressionUtils.as(JPAExpressions.select(payment.paymentamount.sum())
                                .from(investType)
                                .join(payment)
                                .on(investType.id.eq(payment.rewardType.id))
                                .where(investment.id.eq(investType.investment.id))
                                .groupBy(investType.investment.id),"paymentamountSum")
                )
                .from(investment)
                .where(whereCondition)
                .groupBy(investment.id)
                .orderBy(investment.id.desc())
                .limit(10)
                .fetch();
    }

    public List<Tuple> findContinueAll() {
        QReward reward = QReward.reward;
        QRewardType rewardType = QRewardType.rewardType;
        QPayment payment = QPayment.payment;
        LocalDate today = LocalDate.now();

        return jpaQueryFactory
                .select(
                        reward,
                        ExpressionUtils.as(JPAExpressions.select(payment.paymentamount.sum())
                                .from(rewardType)
                                .join(payment)
                                .on(rewardType.id.eq(payment.rewardType.id))
                                .where(reward.id.eq(rewardType.reward.id))
                                .groupBy(rewardType.reward.id),"paymentamountSum")
                )
                .from(reward)
                .where(reward.projDateStart.loe(Date.valueOf(today)), reward.projDateEnd.goe(Date.valueOf(today)))
                .groupBy(reward.id)
                .orderBy(reward.id.desc())
                .fetch();
    }
    public List<Tuple> findContinueAll2() {
        QInvestment investment = QInvestment.investment;
        QInvestType investType = QInvestType.investType;
        QPayment payment = QPayment.payment;
        LocalDate today = LocalDate.now();

        return jpaQueryFactory
                .select(
                        investment,
                        ExpressionUtils.as(JPAExpressions.select(payment.paymentamount.sum())
                                .from(investType)
                                .join(payment)
                                .on(investType.id.eq(payment.rewardType.id))
                                .where(investment.id.eq(investType.investment.id))
                                .groupBy(investType.investment.id),"paymentamountSum")
                )
                .from(investment)
                .where(investment.investProjDateStart.loe(Date.valueOf(today)), investment.investProjDateEnd.goe(Date.valueOf(today)))
                .groupBy(investment.id)
                .orderBy(investment.id.desc())
                .limit(10)
                .fetch();
    }
}
