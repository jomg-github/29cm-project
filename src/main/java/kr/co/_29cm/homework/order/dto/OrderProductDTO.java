package kr.co._29cm.homework.order.dto;

import kr.co._29cm.homework.order.entity.OrderProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderProductDTO {
    private Long orderId;
    private Long productId;
    private Integer orderProductQuantity;
    private Long amount;

    @Builder
    public OrderProductDTO(OrderProductEntity orderProductEntity) {
        this.orderId = orderProductEntity.getOrderEntity().getOrderId();
        this.productId = orderProductEntity.getProductId();
        this.orderProductQuantity = orderProductEntity.getOrderProductQuantity();
        this.amount = orderProductEntity.getAmount();
    }
}
