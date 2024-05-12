package com.example.store.services;

import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.entities.User;

import java.util.List;

public interface OrderService {
    OrderResponseDTO create(OrderRequestDTO orderRequestDTO, User user);

    OrderResponseDTO update(Long id, OrderRequestDTO orderRequestDTO);

    OrderResponseDTO get(Long id);

    List<OrderResponseDTO> getAll(Long id);

    void deleteOrder(Long id);
}
