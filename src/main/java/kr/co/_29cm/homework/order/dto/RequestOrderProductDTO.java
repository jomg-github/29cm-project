package kr.co._29cm.homework.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class RequestOrderProductDTO {
    private Long productId;
    private Integer orderProductQuantity;
}
