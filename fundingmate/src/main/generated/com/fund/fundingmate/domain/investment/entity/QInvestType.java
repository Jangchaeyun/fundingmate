package com.fund.fundingmate.domain.investment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvestType is a Querydsl query type for InvestType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvestType extends EntityPathBase<InvestType> {

    private static final long serialVersionUID = 679123745L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvestType investType = new QInvestType("investType");

    public final StringPath investAmount = createString("investAmount");

    public final StringPath investLimit = createString("investLimit");

    public final StringPath investLimitCount = createString("investLimitCount");

    public final QInvestment investment;

    public final NumberPath<Long> investOptNo = createNumber("investOptNo", Long.class);

    public QInvestType(String variable) {
        this(InvestType.class, forVariable(variable), INITS);
    }

    public QInvestType(Path<? extends InvestType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvestType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvestType(PathMetadata metadata, PathInits inits) {
        this(InvestType.class, metadata, inits);
    }

    public QInvestType(Class<? extends InvestType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.investment = inits.isInitialized("investment") ? new QInvestment(forProperty("investment"), inits.get("investment")) : null;
    }

}

