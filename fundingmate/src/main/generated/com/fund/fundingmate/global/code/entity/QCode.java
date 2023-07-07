package com.fund.fundingmate.global.code.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCode is a Querydsl query type for Code
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCode extends EntityPathBase<Code> {

    private static final long serialVersionUID = -1545109926L;

    public static final QCode code = new QCode("code");

    public final NumberPath<Long> codeId = createNumber("codeId", Long.class);

    public final StringPath fileClassification = createString("fileClassification");

    public final StringPath fileName = createString("fileName");

    public final DateTimePath<java.util.Date> fileRegistrationDate = createDateTime("fileRegistrationDate", java.util.Date.class);

    public QCode(String variable) {
        super(Code.class, forVariable(variable));
    }

    public QCode(Path<? extends Code> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCode(PathMetadata metadata) {
        super(Code.class, metadata);
    }

}

