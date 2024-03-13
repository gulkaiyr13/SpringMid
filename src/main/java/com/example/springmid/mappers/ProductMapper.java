package com.example.springmid.mappers;
import com.example.springmid.dto.ProductDTO;
import com.example.springmid.dto.UserDTO;
import com.example.springmid.entities.Product;
import com.example.springmid.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productDtoToProduct (ProductDTO productDTO);
    ProductDTO productToProductDto (Product product);
}
