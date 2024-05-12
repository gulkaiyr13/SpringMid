package com.example.store.controllers;

import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(
        name = "Controller of products"
)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(
            summary = "Product creating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.create(productRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Product updating"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO productRequestDTO) {
        return new ResponseEntity<>(productService.update(id, productRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Product getting by id"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.get(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Getting all products"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Product deleting"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
