package kr.co._29cm.homework.order.service;

import kr.co._29cm.homework.command.service.CommandService;
import kr.co._29cm.homework.global.exception.NoSuchDataException;
import kr.co._29cm.homework.global.exception.SoldOutException;
import kr.co._29cm.homework.order.dto.CreateOrderDTO;
import kr.co._29cm.homework.order.dto.OrderDTO;
import kr.co._29cm.homework.order.dto.OrderProductDTO;
import kr.co._29cm.homework.order.dto.RequestOrderProductDTO;
import kr.co._29cm.homework.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    ProductService productService;
    @Autowired
    CommandService commandService;

    @Test
    void 유효_상품_테스트_X() {
        // given
        Long invaildProductId = 1234567L;

        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();
        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(invaildProductId)
                .orderProductQuantity(1)
                .build());

        // when
        // then
        assertThatThrownBy(() -> orderService.createOrder(CreateOrderDTO.builder()
                .requestOrderProductDTOList(requestOrderProductDTOList)
                .build()
        )).isInstanceOf(NoSuchDataException.class);
    }

    @Test
    void 재고보다_많은_수량_구매_테스트_X() {
        // given
        Long productId = 748943L;

        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();
        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(productId)
                .orderProductQuantity(100)
                .build());

        // when
        // then
        assertThatThrownBy(() -> orderService.createOrder(CreateOrderDTO.builder()
                .requestOrderProductDTOList(requestOrderProductDTOList)
                .build()
        )).isInstanceOf(SoldOutException.class);
    }

    @Test
    void 정상_주문_테스트_O() {
        // given
        Long productId = 748943L;
        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();
        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(productId)
                .orderProductQuantity(1)
                .build());

        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(productId)
                .orderProductQuantity(3)
                .build());

        // when
        Long orderId = orderService.createOrder(CreateOrderDTO.builder()
                .requestOrderProductDTOList(requestOrderProductDTOList)
                .build());

        // then
        OrderDTO orderDTO = orderService.getOrder(orderId);
        List<OrderProductDTO> orderProductDTOList = orderDTO.getOrderProductDTOList();
        Long orderTotalAmount = orderProductDTOList.stream().mapToLong(OrderProductDTO::getAmount).sum();

        assertThat(orderTotalAmount).isEqualTo(orderDTO.getOrderTotalAmount());
    }

    @Test
    void 배송비_적용_테스트() {
        // given
        Long productId_price19000 = 748943L;
        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();
        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(productId_price19000)
                .orderProductQuantity(1)
                .build());

        // when
        Long orderId = orderService.createOrder(CreateOrderDTO.builder()
                .requestOrderProductDTOList(requestOrderProductDTOList)
                .build());

        // then
        OrderDTO orderDTO = orderService.getOrder(orderId);
        List<OrderProductDTO> orderProductDTOList = orderDTO.getOrderProductDTOList();
        Long orderTotalAmount = orderProductDTOList.stream().mapToLong(OrderProductDTO::getAmount).sum();

        assertThat(orderTotalAmount).isEqualTo(orderDTO.getOrderTotalAmount());
    }

    @Test
    void 동시_주문_재고_차감_테스트() throws InterruptedException {
        // given
        Long productId_quantity50 = 782858L;

        // when
        int numberOfThreads = 50;
        int orderProductQuantity = 1;
        List<RequestOrderProductDTO> requestOrderProductDTOList = new ArrayList<>();
        requestOrderProductDTOList.add(RequestOrderProductDTO.builder()
                .productId(productId_quantity50)
                .orderProductQuantity(orderProductQuantity)
                .build());

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    orderService.createOrder(CreateOrderDTO.builder()
                            .requestOrderProductDTOList(requestOrderProductDTOList)
                            .build());
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        assertThat(productService.getProduct(productId_quantity50).getProductQuantity()).isEqualTo(0);
    }

}