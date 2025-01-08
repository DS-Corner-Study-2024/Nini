package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProviderRepositoryTest {
    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void relationshipTest(){
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("Corner");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(1000);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("모자");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> productList = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product: productList){
            System.out.println(product);
        }
    }
}