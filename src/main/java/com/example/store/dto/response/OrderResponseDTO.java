package com.example.store.dto.response;

import com.example.store.entities.Product;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private ProductResponseDTO product;
    private double totalPrice;
}
