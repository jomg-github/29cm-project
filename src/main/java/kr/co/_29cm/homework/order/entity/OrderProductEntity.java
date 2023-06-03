package kr.co._29cm.homework.order.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ORDER_PRODUCT")
@Getter
@NoArgsConstructor
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_PRODUCT_ID")
    private Long orderProductId;

    @Column(name = "ORDER_PRDUCT_QUANTITY", nullable = false)
    private Integer orderProductQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity orderEntity;

    @Column(name = "PRODUCT_ID", nullable = false)
    private Long productId;

    @Column(name = "AMOUNT", nullable = false)
    private Long amount;

    @Builder
    public OrderProductEntity(OrderEntity orderEntity, Long productId, Integer orderProductQuantity, Long amount) {
        this.orderEntity = orderEntity;
        this.productId = productId;
        this.orderProductQuantity = orderProductQuantity;
        this.amount = amount;
    }
}
