package com.example.springmid.services;

import com.example.springmid.dto.response.OrderResponseDTO;
import com.example.springmid.dto.reuest.OrderRequestDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO create(OrderRequestDTO orderRequestDTO);
    OrderResponseDTO update(Long id, OrderRequestDTO orderRequestDTO);
    OrderResponseDTO get(Long id);
    List<OrderResponseDTO> getAll(Long id);
    void deleteOrder(Long id);

}
