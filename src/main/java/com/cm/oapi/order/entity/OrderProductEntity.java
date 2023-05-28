package com.cm.oapi.order.entity;

import com.cm.oapi.product.entity.ProductEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_ORDER_PRODUCT")
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private ProductEntity productEntity;

    @Column(name = "ORDER_PRDUCT_QUANTITY", nullable = false)
    private Integer orderProductQuantity;
}
