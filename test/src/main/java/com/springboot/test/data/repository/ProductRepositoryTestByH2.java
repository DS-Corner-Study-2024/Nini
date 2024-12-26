package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProductRepositoryTestByH2 {

    // @DataJpaTest 어노테이션 선언 -> 리포지토리 정상 주입 가능
    @Autowired
    private ProductRepository productRepository;

    // Given-When-Then 패턴으로 작성

    // 데이터베이스 저장 테스트 코드
    @Test
    void saveTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getStock(), savedProduct.getStock());
    }


    // 데이터베이스 조회 테스트 코드
    @Test
    void selectTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        // 데이터베이스 조회 테스트를 위해 Given 절에서 객체를 데이터베이스에 저장
        Product savedProduct = productRepository.saveAndFlush(product);

        // when
        // 조회 메서드를 호출해서 테스트를 진행하고 이후 코드에서 데이터를 비교하며 검증 수행
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        // then
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getStock(), foundProduct.getStock());
    }
}