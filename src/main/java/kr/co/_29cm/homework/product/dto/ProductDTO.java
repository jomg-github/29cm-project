package kr.co._29cm.homework.product.dto;

import kr.co._29cm.homework.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDTO {
    private Long productId;
    private String productName;
    private Long productPrice;
    private Integer productQuantity;

    @Builder
    public ProductDTO(ProductEntity productEntity) {
        this.productId = productEntity.getProductId();
        this.productName = productEntity.getProductName();
        this.productPrice = productEntity.getProductPrice();
        this.productQuantity = productEntity.getProductQuantity();
    }
}
