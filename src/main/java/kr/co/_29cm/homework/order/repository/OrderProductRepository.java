package kr.co._29cm.homework.order.repository;

import kr.co._29cm.homework.order.entity.OrderEntity;
import kr.co._29cm.homework.order.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {
    List<OrderProductEntity> findAllByOrderEntity(OrderEntity orderEntity);
}
