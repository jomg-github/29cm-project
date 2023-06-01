package kr.co._29cm.homework.product.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @AfterEach
    void 재고_초기화() {
        Long productId_quantity50 = 782858L;
        productService.setProductQuantity(productId_quantity50, 50);
    }

    @Test
    void 재고_차감_테스트() {
        // given
        Long productId_quantity50 = 782858L;

        // when
        int numberOfThreads = 50;
        int orderProductQuantity = 1;

        for (int i = 0; i < numberOfThreads; i++) {
            productService.decreaseProductQuantity(productId_quantity50, orderProductQuantity);
        }

        assertThat(productService.getProduct(productId_quantity50).getProductQuantity()).isLessThanOrEqualTo(0);
    }

    @Test
    void 동시_재고_차감_테스트() throws InterruptedException {
        // given
        Long productId_quantity50 = 782858L;

        // when
        int numberOfThreads = 50;
        int orderProductQuantity = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    productService.decreaseProductQuantity(productId_quantity50, orderProductQuantity);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        assertThat(productService.getProduct(productId_quantity50).getProductQuantity()).isLessThanOrEqualTo(0);
    }
}