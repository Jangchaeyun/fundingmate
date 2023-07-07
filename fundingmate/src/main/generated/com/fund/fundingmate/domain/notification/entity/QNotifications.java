package com.fund.fundingmate.domain.notification.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotifications is a Querydsl query type for Notifications
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotifications extends EntityPathBase<Notifications> {

    private static final long serialVersionUID = 1859828414L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotifications notifications = new QNotifications("notifications");

    public final NumberPath<Long> notifationNo = createNumber("notifationNo", Long.class);

    public final StringPath notificationFavorites = createString("notificationFavorites");

    public final StringPath notificationFunded = createString("notificationFunded");

    public final StringPath notificationRewards = createString("notificationRewards");

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QNotifications(String variable) {
        this(Notifications.class, forVariable(variable), INITS);
    }

    public QNotifications(Path<? extends Notifications> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotifications(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotifications(PathMetadata metadata, PathInits inits) {
        this(Notifications.class, metadata, inits);
    }

    public QNotifications(Class<? extends Notifications> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

