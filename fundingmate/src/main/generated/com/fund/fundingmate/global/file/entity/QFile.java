package com.fund.fundingmate.global.file.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1013460728L;

    public static final QFile file = new QFile("file");

    public final StringPath fileClassfication = createString("fileClassfication");

    public final NumberPath<Long> fileId = createNumber("fileId", Long.class);

    public final StringPath fileOriginalName = createString("fileOriginalName");

    public final StringPath filePath = createString("filePath");

    public final DateTimePath<java.util.Date> fileRegistrationDate = createDateTime("fileRegistrationDate", java.util.Date.class);

    public final StringPath fileSavedName = createString("fileSavedName");

    public final StringPath fileSize = createString("fileSize");

    public QFile(String variable) {
        super(File.class, forVariable(variable));
    }

    public QFile(Path<? extends File> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFile(PathMetadata metadata) {
        super(File.class, metadata);
    }

}

