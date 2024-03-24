package com.example.springmid;

import com.example.springmid.entities.Product;
import com.example.springmid.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindById(){
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(50.0);
        Product savedProduct = productRepository.save(product);

        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());

        assertTrue(optionalProduct.isPresent());
        assertEquals(savedProduct.getName(), optionalProduct.get().getName());
        assertEquals(savedProduct.getPrice(), optionalProduct.get().getPrice());
    }
}
