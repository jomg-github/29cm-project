package kr.co._29cm.homework.order.repository;

import kr.co._29cm.homework.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
