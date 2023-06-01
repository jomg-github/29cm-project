package kr.co._29cm.homework.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_ORDER")
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "ORDER_TOTAL_AMOUNT", nullable = false)
    private Long orderTotalAmount;

    @Column(name = "SHIPPING_FEE", nullable = false)
    private Long shippingFee;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER)
    private List<OrderProductEntity> orderProductEntities = new ArrayList<>();

    @Builder
    public OrderEntity() {
        this.orderTotalAmount = 0L;
        this.shippingFee = 0L;
    }

    public void addOrderAmount(Long orderAmount) {
        this.orderTotalAmount += orderAmount;
    }

    public boolean isFreeShipping() {
        return this.orderTotalAmount > 50000;
    }

    public void applyShippingFee(Long shippingFee) {
        this.shippingFee = shippingFee;
    }

}
