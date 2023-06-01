package kr.co._29cm.homework.product.entity;

import kr.co._29cm.homework.global.exception.SoldOutException;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TB_PRODUCT")
@Getter
@DynamicUpdate
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Long productPrice;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private Integer productQuantity;

    public void decreaseProductQuantity(Integer orderProductQuantity) {
        if (this.productQuantity - orderProductQuantity < 0) {
            throw new SoldOutException("SoldOutException 발생. 주문한 상품량이 재고량보다 큽니다.");
        }
        this.productQuantity = this.productQuantity - orderProductQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
