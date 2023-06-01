package kr.co._29cm.homework.order.dto;

import kr.co._29cm.homework.order.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CreateOrderProductDTO {
    private OrderEntity orderEntity;
    private Long productId;
    private Integer orderProductQuantity;
    private Long amount;
}
