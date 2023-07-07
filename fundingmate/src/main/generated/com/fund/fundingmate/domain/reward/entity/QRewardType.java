package com.fund.fundingmate.domain.reward.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRewardType is a Querydsl query type for RewardType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewardType extends EntityPathBase<RewardType> {

    private static final long serialVersionUID = -1351812169L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRewardType rewardType = new QRewardType("rewardType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReward reward;

    public final NumberPath<Integer> rewardAmount = createNumber("rewardAmount", Integer.class);

    public final NumberPath<Integer> rewardAvailableCount = createNumber("rewardAvailableCount", Integer.class);

    public final BooleanPath rewardAvailableLimit = createBoolean("rewardAvailableLimit");

    public final StringPath rewardContent = createString("rewardContent");

    public final StringPath rewardDeliveryDate = createString("rewardDeliveryDate");

    public final QRewardOption rewardOption;

    public final BooleanPath rewardShipAddress = createBoolean("rewardShipAddress");

    public final StringPath rewardTitle = createString("rewardTitle");

    public QRewardType(String variable) {
        this(RewardType.class, forVariable(variable), INITS);
    }

    public QRewardType(Path<? extends RewardType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRewardType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRewardType(PathMetadata metadata, PathInits inits) {
        this(RewardType.class, metadata, inits);
    }

    public QRewardType(Class<? extends RewardType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reward = inits.isInitialized("reward") ? new QReward(forProperty("reward"), inits.get("reward")) : null;
        this.rewardOption = inits.isInitialized("rewardOption") ? new QRewardOption(forProperty("rewardOption"), inits.get("rewardOption")) : null;
    }

}

