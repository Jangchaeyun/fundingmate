package com.fund.fundingmate.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentList is a Querydsl query type for PaymentList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentList extends EntityPathBase<PaymentList> {

    private static final long serialVersionUID = 713115939L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentList paymentList = new QPaymentList("paymentList");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.fund.fundingmate.domain.investment.entity.QInvestment investment;

    public final QPayment payment;

    public final com.fund.fundingmate.domain.reward.entity.QReward reward;

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QPaymentList(String variable) {
        this(PaymentList.class, forVariable(variable), INITS);
    }

    public QPaymentList(Path<? extends PaymentList> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentList(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentList(PathMetadata metadata, PathInits inits) {
        this(PaymentList.class, metadata, inits);
    }

    public QPaymentList(Class<? extends PaymentList> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.investment = inits.isInitialized("investment") ? new com.fund.fundingmate.domain.investment.entity.QInvestment(forProperty("investment"), inits.get("investment")) : null;
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.reward = inits.isInitialized("reward") ? new com.fund.fundingmate.domain.reward.entity.QReward(forProperty("reward"), inits.get("reward")) : null;
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

