package kr.co._29cm.homework.product.repository;

import kr.co._29cm.homework.product.entity.ProductEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findAllByOrderByProductIdDesc();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<ProductEntity> findById(Long aLong);
}
