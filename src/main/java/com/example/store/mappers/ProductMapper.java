package com.example.store.mappers;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO productRequestDTO);

    ProductResponseDTO toResponseDTO(Product product);
}
