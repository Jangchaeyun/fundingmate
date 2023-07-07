package com.fund.fundingmate.domain.payment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = 528555749L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final StringPath birthday = createString("birthday");

    public final StringPath cardnumber = createString("cardnumber");

    public final StringPath cardpassword = createString("cardpassword");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath payenddate = createString("payenddate");

    public final NumberPath<Integer> paymentamount = createNumber("paymentamount", Integer.class);

    public final BooleanPath paymentcode = createBoolean("paymentcode");

    public final StringPath payperiod = createString("payperiod");

    public final StringPath shippingaddressdesc = createString("shippingaddressdesc");

    public final StringPath shippingadress = createString("shippingadress");

    public final com.fund.fundingmate.domain.user.entity.QUser user;

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.fund.fundingmate.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

