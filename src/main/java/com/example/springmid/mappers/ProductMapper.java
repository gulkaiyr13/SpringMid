package com.example.springmid.mappers;
import com.example.springmid.dto.ProductDTO;
import com.example.springmid.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDto (Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
