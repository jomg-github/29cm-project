package kr.co._29cm.homework.order.service;

import kr.co._29cm.homework.global.exception.NoSuchDataException;
import kr.co._29cm.homework.order.dto.OrderDTO;
import kr.co._29cm.homework.order.dto.RequestOrderProductDTO;
import kr.co._29cm.homework.order.entity.OrderEntity;
import kr.co._29cm.homework.order.repository.OrderRepository;
import kr.co._29cm.homework.product.dto.ProductDTO;
import kr.co._29cm.homework.product.service.ProductService;
import kr.co._29cm.homework.order.dto.CreateOrderDTO;
import kr.co._29cm.homework.order.dto.CreateOrderProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class OrderService {
    private static final Long SHIPPING_FEE = 2500L;
    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;
    private final ProductService productService;

    /**
     * 주문 내역 조회
     */
    public OrderDTO getOrder(Long orderId) {
        return OrderDTO.builder()
                .orderEntity(orderRepository.findById(orderId).orElseThrow(NoSuchDataException::new))
                .build();
    }

    /**
     * 주문 생성
     */
    @Transactional
    public Long createOrder(CreateOrderDTO createOrderDTO) {
        List<RequestOrderProductDTO> requestOrderProductDTOList = createOrderDTO.getRequestOrderProductDTOList();

        // transaction
        // 주문 생성
        OrderEntity orderEntity = orderRepository.save(new OrderEntity());

        // 주문-상품 데이터 추가 및 주문된 상품들 재고 감소
        for (RequestOrderProductDTO requestOrderProductDTO : requestOrderProductDTOList) {
            ProductDTO targetProductDTO = productService.getProduct(requestOrderProductDTO.getProductId());
            Long amount = targetProductDTO.getProductPrice() * requestOrderProductDTO.getOrderProductQuantity();
            orderProductService.createOrderProduct(
                    CreateOrderProductDTO.builder()
                            .orderEntity(orderEntity)
                            .productId(requestOrderProductDTO.getProductId())
                            .orderProductQuantity(requestOrderProductDTO.getOrderProductQuantity())
                            .amount(amount)
                            .build());
            orderEntity.addOrderAmount(amount);

            productService.decreaseProductQuantity(requestOrderProductDTO.getProductId(), requestOrderProductDTO.getOrderProductQuantity());
        }

        if (!orderEntity.isFreeShipping()) {
            orderEntity.applyShippingFee(SHIPPING_FEE);
        }

        return orderEntity.getOrderId();
    }
}
