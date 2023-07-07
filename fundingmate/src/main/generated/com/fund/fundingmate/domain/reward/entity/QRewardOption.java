package com.fund.fundingmate.domain.reward.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRewardOption is a Querydsl query type for RewardOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewardOption extends EntityPathBase<RewardOption> {

    private static final long serialVersionUID = 2132265394L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRewardOption rewardOption = new QRewardOption("rewardOption");

    public final StringPath getRewardOptCon = createString("getRewardOptCon");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath rewardOptName = createString("rewardOptName");

    public final QRewardType rewardType;

    public QRewardOption(String variable) {
        this(RewardOption.class, forVariable(variable), INITS);
    }

    public QRewardOption(Path<? extends RewardOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRewardOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRewardOption(PathMetadata metadata, PathInits inits) {
        this(RewardOption.class, metadata, inits);
    }

    public QRewardOption(Class<? extends RewardOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rewardType = inits.isInitialized("rewardType") ? new QRewardType(forProperty("rewardType"), inits.get("rewardType")) : null;
    }

}

