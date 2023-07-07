package com.fund.fundingmate.domain.investment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvestment is a Querydsl query type for Investment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvestment extends EntityPathBase<Investment> {

    private static final long serialVersionUID = 679849253L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvestment investment = new QInvestment("investment");

    public final StringPath accNumber = createString("accNumber");

    public final StringPath bank = createString("bank");

    public final StringPath blogUrl = createString("blogUrl");

    public final StringPath businessAddress = createString("businessAddress");

    public final StringPath depositorName = createString("depositorName");

    public final DateTimePath<java.util.Date> expectedPaymentDate = createDateTime("expectedPaymentDate", java.util.Date.class);

    public final StringPath facebookUrl = createString("facebookUrl");

    public final StringPath instagramUrl = createString("instagramUrl");

    public final StringPath investBankAccountCopyImgSavedName = createString("investBankAccountCopyImgSavedName");

    public final StringPath investCategory = createString("investCategory");

    public final StringPath investContentImgSavedName = createString("investContentImgSavedName");

    public final StringPath investEmail = createString("investEmail");

    public final StringPath investIdBusinessLicenseImgSavedName = createString("investIdBusinessLicenseImgSavedName");

    public final StringPath investItemBenefit = createString("investItemBenefit");

    public final StringPath investItemBusinessValue = createString("investItemBusinessValue");

    public final StringPath investItemIntro = createString("investItemIntro");

    public final StringPath investItemValue = createString("investItemValue");

    public final NumberPath<Long> investNo = createNumber("investNo", Long.class);

    public final StringPath investProjContent = createString("investProjContent");

    public final DateTimePath<java.util.Date> investProjDateEnd = createDateTime("investProjDateEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> investProjDateStart = createDateTime("investProjDateStart", java.util.Date.class);

    public final StringPath investProjKeyword = createString("investProjKeyword");

    public final StringPath investProjName = createString("investProjName");

    public final StringPath investRepImgSavedName = createString("investRepImgSavedName");

    public final NumberPath<Integer> investTargetAmount = createNumber("investTargetAmount", Integer.class);

    public final StringPath investVideoUrl = createString("investVideoUrl");

    public final NumberPath<Integer> rateOfReturn = createNumber("rateOfReturn", Integer.class);

    public final StringPath repaymentMethod = createString("repaymentMethod");

    public final StringPath taxBillEmail = createString("taxBillEmail");

    public final StringPath twitterUrl = createString("twitterUrl");

    public final StringPath useOfFunds = createString("useOfFunds");

    public final DateTimePath<java.util.Date> useOfFundsDateEnd = createDateTime("useOfFundsDateEnd", java.util.Date.class);

    public final DateTimePath<java.util.Date> useOfFundsDateStart = createDateTime("useOfFundsDateStart", java.util.Date.class);

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public final StringPath websiteUrl = createString("websiteUrl");

    public QInvestment(String variable) {
        this(Investment.class, forVariable(variable), INITS);
    }

    public QInvestment(Path<? extends Investment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvestment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvestment(PathMetadata metadata, PathInits inits) {
        this(Investment.class, metadata, inits);
    }

    public QInvestment(Class<? extends Investment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

