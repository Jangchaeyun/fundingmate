package com.fund.fundingmate.domain.investment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvestReply is a Querydsl query type for InvestReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvestReply extends EntityPathBase<InvestReply> {

    private static final long serialVersionUID = -424442909L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvestReply investReply = new QInvestReply("investReply");

    public final QInvestComment comment;

    public final QInvestment investment;

    public final StringPath repContent = createString("repContent");

    public final NumberPath<Long> repNo = createNumber("repNo", Long.class);

    public final DateTimePath<java.util.Date> repRegistrationDate = createDateTime("repRegistrationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> repRevisionDate = createDateTime("repRevisionDate", java.util.Date.class);

    public final StringPath repTitle = createString("repTitle");

    public QInvestReply(String variable) {
        this(InvestReply.class, forVariable(variable), INITS);
    }

    public QInvestReply(Path<? extends InvestReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvestReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvestReply(PathMetadata metadata, PathInits inits) {
        this(InvestReply.class, metadata, inits);
    }

    public QInvestReply(Class<? extends InvestReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QInvestComment(forProperty("comment"), inits.get("comment")) : null;
        this.investment = inits.isInitialized("investment") ? new QInvestment(forProperty("investment"), inits.get("investment")) : null;
    }

}

