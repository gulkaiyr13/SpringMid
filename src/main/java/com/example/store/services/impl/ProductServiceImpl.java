package com.example.store.services.impl;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.entities.Product;
import com.example.store.exceptions.GeneralException;
import com.example.store.mappers.ProductMapper;
import com.example.store.repositories.ProductRepository;
import com.example.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        return productMapper.toResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException("Product not found"));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setDescription(productRequestDTO.getDescription());

        return productMapper.toResponseDTO(productRepository.save(product));
    }

    @Override
    public ProductResponseDTO get(Long id) {
        Product product =  productRepository.findById(id).orElseThrow(() -> new GeneralException("Product not found"));
        return productMapper.toResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        return productRepository.findAll().stream().map(productMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
