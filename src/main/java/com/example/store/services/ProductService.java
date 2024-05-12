package com.example.store.services;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO);

    ProductResponseDTO get(Long id);

    List<ProductResponseDTO> getAll();

    void deleteProduct(Long id);
}
