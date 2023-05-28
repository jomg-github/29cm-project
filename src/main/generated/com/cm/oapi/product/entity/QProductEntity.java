package com.cm.oapi.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductEntity is a Querydsl query type for ProductEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductEntity extends EntityPathBase<ProductEntity> {

    private static final long serialVersionUID = 2052243954L;

    public static final QProductEntity productEntity = new QProductEntity("productEntity");

    public final ListPath<com.cm.oapi.order.entity.OrderProductEntity, com.cm.oapi.order.entity.QOrderProductEntity> orderProductEntities = this.<com.cm.oapi.order.entity.OrderProductEntity, com.cm.oapi.order.entity.QOrderProductEntity>createList("orderProductEntities", com.cm.oapi.order.entity.OrderProductEntity.class, com.cm.oapi.order.entity.QOrderProductEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath productName = createString("productName");

    public final NumberPath<Long> productPrice = createNumber("productPrice", Long.class);

    public final NumberPath<Integer> productQuantity = createNumber("productQuantity", Integer.class);

    public QProductEntity(String variable) {
        super(ProductEntity.class, forVariable(variable));
    }

    public QProductEntity(Path<? extends ProductEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductEntity(PathMetadata metadata) {
        super(ProductEntity.class, metadata);
    }

}

