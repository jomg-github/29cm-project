package kr.co._29cm.homework.order.service;

import kr.co._29cm.homework.order.dto.CreateOrderProductDTO;
import kr.co._29cm.homework.order.entity.OrderProductEntity;
import kr.co._29cm.homework.order.repository.OrderProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderProductService {
    private final OrderProductRepository orderProductRepository;

    /**
     * 주문-상품 등록
     */
    @Transactional
    public OrderProductEntity createOrderProduct(CreateOrderProductDTO createOrderProductDTO) {
        return orderProductRepository.save(
                OrderProductEntity.builder()
                        .orderEntity(createOrderProductDTO.getOrderEntity())
                        .orderProductQuantity(createOrderProductDTO.getOrderProductQuantity())
                        .productId(createOrderProductDTO.getProductId())
                        .amount(createOrderProductDTO.getAmount())
                        .build());
    }
}
