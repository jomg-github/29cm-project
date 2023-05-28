package com.cm.oapi.product.entity;

import com.cm.oapi.order.entity.OrderProductEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "TB_PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", length = 255, nullable = false)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Long productPrice;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private Integer productQuantity;

    @OneToMany(mappedBy = "productEntity")
    private List<OrderProductEntity> orderProductEntities;
}
