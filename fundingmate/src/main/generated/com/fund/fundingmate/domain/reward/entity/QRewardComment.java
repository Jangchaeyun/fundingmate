package com.fund.fundingmate.domain.reward.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRewardComment is a Querydsl query type for RewardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewardComment extends EntityPathBase<RewardComment> {

    private static final long serialVersionUID = -419375934L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRewardComment rewardComment = new QRewardComment("rewardComment");

    public final StringPath comContent = createString("comContent");

    public final DateTimePath<java.util.Date> comRegistrationDate = createDateTime("comRegistrationDate", java.util.Date.class);

    public final DateTimePath<java.util.Date> comRevisionDate = createDateTime("comRevisionDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<RewardReply, QRewardReply> replies = this.<RewardReply, QRewardReply>createList("replies", RewardReply.class, QRewardReply.class, PathInits.DIRECT2);

    public final QReward reward;

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QRewardComment(String variable) {
        this(RewardComment.class, forVariable(variable), INITS);
    }

    public QRewardComment(Path<? extends RewardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRewardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRewardComment(PathMetadata metadata, PathInits inits) {
        this(RewardComment.class, metadata, inits);
    }

    public QRewardComment(Class<? extends RewardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reward = inits.isInitialized("reward") ? new QReward(forProperty("reward"), inits.get("reward")) : null;
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

