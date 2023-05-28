package com.cm.oapi.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_ORDER")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_TOTAL_AMOUNT")
    private Long orderTotalAmount;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderProductEntity> orderProductEntities;
}
