package com.fund.fundingmate.domain.reward.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRewardReply is a Querydsl query type for RewardReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewardReply extends EntityPathBase<RewardReply> {

    private static final long serialVersionUID = 1041053197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRewardReply rewardReply = new QRewardReply("rewardReply");

    public final QRewardComment comment;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath repContent = createString("repContent");

    public final DateTimePath<java.util.Date> repRegistrationDate = createDateTime("repRegistrationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> repRevisionDate = createDateTime("repRevisionDate", java.util.Date.class);

    public final QReward reward;

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QRewardReply(String variable) {
        this(RewardReply.class, forVariable(variable), INITS);
    }

    public QRewardReply(Path<? extends RewardReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRewardReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRewardReply(PathMetadata metadata, PathInits inits) {
        this(RewardReply.class, metadata, inits);
    }

    public QRewardReply(Class<? extends RewardReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QRewardComment(forProperty("comment"), inits.get("comment")) : null;
        this.reward = inits.isInitialized("reward") ? new QReward(forProperty("reward"), inits.get("reward")) : null;
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

