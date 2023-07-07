package com.fund.fundingmate.domain.investment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvestComment is a Querydsl query type for InvestComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvestComment extends EntityPathBase<InvestComment> {

    private static final long serialVersionUID = -11860712L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvestComment investComment1 = new QInvestComment("investComment1");

    public final StringPath commContent = createString("commContent");

    public final NumberPath<Long> commNo = createNumber("commNo", Long.class);

    public final DateTimePath<java.util.Date> commRegistrationDate = createDateTime("commRegistrationDate", java.util.Date.class);

    public final StringPath commTitle = createString("commTitle");

    public final DateTimePath<java.util.Date> comRevisionDate = createDateTime("comRevisionDate", java.util.Date.class);

    public final QInvestComment investComment;

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QInvestComment(String variable) {
        this(InvestComment.class, forVariable(variable), INITS);
    }

    public QInvestComment(Path<? extends InvestComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvestComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvestComment(PathMetadata metadata, PathInits inits) {
        this(InvestComment.class, metadata, inits);
    }

    public QInvestComment(Class<? extends InvestComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.investComment = inits.isInitialized("investComment") ? new QInvestComment(forProperty("investComment"), inits.get("investComment")) : null;
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

