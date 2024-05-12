package com.example.store.mappers;

import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.entities.Order;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toDTO(Order order);
}
