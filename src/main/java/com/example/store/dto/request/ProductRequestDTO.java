package com.example.store.dto.request;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private double price;
    private String description;
}
