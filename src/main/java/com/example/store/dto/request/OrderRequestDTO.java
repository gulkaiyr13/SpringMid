package com.example.store.dto.request;

import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull(message = "Order must have product")
    @JsonProperty("product_id")
    private Long productId;
}
