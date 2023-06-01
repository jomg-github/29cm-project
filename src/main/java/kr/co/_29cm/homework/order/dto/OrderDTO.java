package kr.co._29cm.homework.order.dto;

import kr.co._29cm.homework.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDTO {
    private Long orderId;
    private Long orderTotalAmount;
    private Long shippingFee;
    private List<OrderProductDTO> orderProductDTOList = new ArrayList<>();

    @Builder
    public OrderDTO(OrderEntity orderEntity) {
        this.orderId = orderEntity.getOrderId();
        this.orderTotalAmount = orderEntity.getOrderTotalAmount();
        this.shippingFee = orderEntity.getShippingFee();
        this.orderProductDTOList = orderEntity.getOrderProductEntities().stream()
                .map(orderProductEntity -> OrderProductDTO.builder().orderProductEntity(orderProductEntity).build())
                .toList();
    }

    public boolean isFreeShipping() {
        return this.orderTotalAmount > 50000;
    }
}
