package com.cm.oapi.order.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderEntity is a Querydsl query type for OrderEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderEntity extends EntityPathBase<OrderEntity> {

    private static final long serialVersionUID = -133443758L;

    public static final QOrderEntity orderEntity = new QOrderEntity("orderEntity");

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final ListPath<OrderProductEntity, QOrderProductEntity> orderProductEntities = this.<OrderProductEntity, QOrderProductEntity>createList("orderProductEntities", OrderProductEntity.class, QOrderProductEntity.class, PathInits.DIRECT2);

    public QOrderEntity(String variable) {
        super(OrderEntity.class, forVariable(variable));
    }

    public QOrderEntity(Path<? extends OrderEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderEntity(PathMetadata metadata) {
        super(OrderEntity.class, metadata);
    }

}

