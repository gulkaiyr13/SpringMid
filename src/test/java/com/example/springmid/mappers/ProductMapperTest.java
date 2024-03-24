package com.example.springmid.mappers;

import com.example.springmid.dto.ProductDTO;
import com.example.springmid.entities.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductMapperTest {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testProductToProductDTO(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(50.0);

        ProductDTO productDTO = productMapper.productToProductDto(product);

        assertNotNull(productDTO);
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getPrice(), productDTO.getPrice());
    }

    @Test
    public void testProductDTOToProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");
        productDTO.setPrice(50.0);

        Product product = productMapper.productDTOToProduct(productDTO);

        assertNotNull(product);
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
        assertEquals(productDTO.getPrice(), product.getPrice());
    }
}
