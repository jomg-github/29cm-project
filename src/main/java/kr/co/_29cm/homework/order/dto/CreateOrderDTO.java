package kr.co._29cm.homework.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class CreateOrderDTO {
    List<RequestOrderProductDTO> requestOrderProductDTOList;
}
