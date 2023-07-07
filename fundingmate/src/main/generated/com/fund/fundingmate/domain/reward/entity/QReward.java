package com.fund.fundingmate.domain.reward.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReward is a Querydsl query type for Reward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReward extends EntityPathBase<Reward> {

    private static final long serialVersionUID = -945705251L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReward reward = new QReward("reward");

    public final StringPath accNumber = createString("accNumber");

    public final StringPath asPhoneNumber = createString("asPhoneNumber");

    public final StringPath bank = createString("bank");

    public final StringPath blogUrl = createString("blogUrl");

    public final StringPath businessAddress = createString("businessAddress");

    public final StringPath countryOfOrigin = createString("countryOfOrigin");

    public final StringPath depositorName = createString("depositorName");

    public final StringPath facebookUrl = createString("facebookUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath instagramUrl = createString("instagramUrl");

    public final StringPath manufacturer = createString("manufacturer");

    public final StringPath modelName = createString("modelName");

    public final StringPath projContent = createString("projContent");

    public final DatePath<java.time.LocalDate> projDateEnd = createDate("projDateEnd", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> projDateStart = createDate("projDateStart", java.time.LocalDate.class);

    public final StringPath projKeyWord = createString("projKeyWord");

    public final StringPath projName = createString("projName");

    public final NumberPath<Integer> projTargetAmout = createNumber("projTargetAmout", Integer.class);

    public final StringPath rewardBankAccountCopyImgSavedName = createString("rewardBankAccountCopyImgSavedName");

    public final StringPath rewardCategory = createString("rewardCategory");

    public final StringPath rewardContact = createString("rewardContact");

    public final StringPath rewardContentImgSavedName = createString("rewardContentImgSavedName");

    public final StringPath rewardEmail = createString("rewardEmail");

    public final StringPath rewardIdBusinessLicenseImgSavedName = createString("rewardIdBusinessLicenseImgSavedName");

    public final StringPath rewardRefundExchangePolicy = createString("rewardRefundExchangePolicy");

    public final StringPath rewardRepImgSavedName = createString("rewardRepImgSavedName");

    public final ListPath<RewardType, QRewardType> rewardTypes = this.<RewardType, QRewardType>createList("rewardTypes", RewardType.class, QRewardType.class, PathInits.DIRECT2);

    public final StringPath rewardVideoAddress = createString("rewardVideoAddress");

    public final StringPath taxBillEmail = createString("taxBillEmail");

    public final StringPath twitterUrl = createString("twitterUrl");

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public final StringPath websiteUrl = createString("websiteUrl");

    public QReward(String variable) {
        this(Reward.class, forVariable(variable), INITS);
    }

    public QReward(Path<? extends Reward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReward(PathMetadata metadata, PathInits inits) {
        this(Reward.class, metadata, inits);
    }

    public QReward(Class<? extends Reward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

