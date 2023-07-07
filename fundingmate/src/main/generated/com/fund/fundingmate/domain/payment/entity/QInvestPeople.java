package com.fund.fundingmate.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInvestPeople is a Querydsl query type for InvestPeople
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInvestPeople extends EntityPathBase<InvestPeople> {

    private static final long serialVersionUID = -1340868251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInvestPeople investPeople = new QInvestPeople("investPeople");

    public final StringPath callnumber = createString("callnumber");

    public final StringPath calltype = createString("calltype");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath securitynumber2 = createString("securitynumber2");

    public final StringPath sercuritynumber1 = createString("sercuritynumber1");

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QInvestPeople(String variable) {
        this(InvestPeople.class, forVariable(variable), INITS);
    }

    public QInvestPeople(Path<? extends InvestPeople> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInvestPeople(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInvestPeople(PathMetadata metadata, PathInits inits) {
        this(InvestPeople.class, metadata, inits);
    }

    public QInvestPeople(Class<? extends InvestPeople> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

