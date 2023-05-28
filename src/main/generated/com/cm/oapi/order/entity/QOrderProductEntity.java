package com.cm.oapi.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProductEntity is a Querydsl query type for OrderProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderProductEntity extends EntityPathBase<OrderProductEntity> {

    private static final long serialVersionUID = 448730979L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductEntity orderProductEntity = new QOrderProductEntity("orderProductEntity");

    public final QOrderEntity orderEntity;

    public final NumberPath<Long> orderProductId = createNumber("orderProductId", Long.class);

    public final NumberPath<Integer> orderProductQuantity = createNumber("orderProductQuantity", Integer.class);

    public final com.cm.oapi.product.entity.QProductEntity productEntity;

    public QOrderProductEntity(String variable) {
        this(OrderProductEntity.class, forVariable(variable), INITS);
    }

    public QOrderProductEntity(Path<? extends OrderProductEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProductEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProductEntity(PathMetadata metadata, PathInits inits) {
        this(OrderProductEntity.class, metadata, inits);
    }

    public QOrderProductEntity(Class<? extends OrderProductEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderEntity = inits.isInitialized("orderEntity") ? new QOrderEntity(forProperty("orderEntity")) : null;
        this.productEntity = inits.isInitialized("productEntity") ? new com.cm.oapi.product.entity.QProductEntity(forProperty("productEntity")) : null;
    }

}

