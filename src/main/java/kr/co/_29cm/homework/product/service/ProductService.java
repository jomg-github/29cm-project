package kr.co._29cm.homework.product.service;

import kr.co._29cm.homework.global.exception.NoSuchDataException;
import kr.co._29cm.homework.product.dto.ProductDTO;
import kr.co._29cm.homework.product.entity.ProductEntity;
import kr.co._29cm.homework.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    /**
     * 상품 전체 조회
     */
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllByOrderByProductIdDesc().stream()
                .map(productEntity -> ProductDTO.builder()
                        .productEntity(productEntity)
                        .build())
                .toList();

    }

    /**
     * 상품 단건 조회
     */
    public ProductDTO getProduct(Long productId) {
        return ProductDTO.builder()
                .productEntity(productRepository.findById(productId).orElseThrow(NoSuchDataException::new))
                .build();
    }

    /**
     * 상품 다건 조회
     */
    public List<ProductDTO> getProducts(List<Long> productIds) {
        return productRepository.findAllById(productIds).stream()
                .map(productEntity -> ProductDTO.builder()
                        .productEntity(productEntity)
                        .build())
                .toList();
    }

    /**
     * 상품 재고 감소
     */
    @Transactional
    public void decreaseProductQuantity(Long productId, Integer decreaseQuantity) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(NoSuchDataException::new);
        productEntity.decreaseProductQuantity(decreaseQuantity);
    }

    /**
     * 상품 재고 설정 (테스트용)
     */
    @Transactional
    public void setProductQuantity(Long productId, Integer productQuantity) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(NoSuchDataException::new);
        productEntity.setProductQuantity(productQuantity);
    }
}
