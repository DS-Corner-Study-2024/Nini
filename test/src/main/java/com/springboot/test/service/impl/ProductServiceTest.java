package com.springboot.test.service.impl;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    // Mockito의 mock() 메서드를 통해 Mock 객체로 ProductRepository 주입
    private ProductServiceImpl productService;

    // 위의 Mock 객체를 기반으로 각 테스트 전 ProductService로 객체를 초기화해서 사용
    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);

    }

    @Test
    void getProductTest() {
        // given -> 테스트에 사용될 Product 엔티티 객체를 생성하고 ProductRepository의 동작에 대한 결과값 리턴 설정
        Product givenProduct = new Product();
        givenProduct.setNumber(123L);
        givenProduct.setName("펜");
        givenProduct.setPrice(1000);
        givenProduct.setStock(1234);

        Mockito.when(productRepository.findById(123L))
                .thenReturn(Optional.of(givenProduct));

        // when
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        // then
        // 리턴받은 ProductResponseDto 객체에 대해서 Assertion을 통해 값을 검증함으로 테스트 목적을 달성하는지 확인
        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        // 검증 보완을 위해 verify()로 부가 검증 시도
        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {
        // given
        // any()
        Mockito.when(productRepository.save(any(Product.class)))
                .then(returnsFirstArg());

        // when
        ProductResponseDto productResponseDto = productService.saveProduct(
                new ProductDto("펜", 1000, 1234));

        // then
        Assertions.assertEquals(productResponseDto.getName(), "펜");
        Assertions.assertEquals(productResponseDto.getPrice(), 1000);
        Assertions.assertEquals(productResponseDto.getStock(), 1234);

        verify(productRepository).save(any());
    }
}