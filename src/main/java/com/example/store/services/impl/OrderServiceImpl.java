package com.example.store.services.impl;

import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.entities.Order;
import com.example.store.entities.Product;
import com.example.store.entities.User;
import com.example.store.exceptions.GeneralException;
import com.example.store.mappers.OrderMapper;
import com.example.store.mappers.ProductMapper;
import com.example.store.repositories.OrderRepository;
import com.example.store.repositories.ProductRepository;
import com.example.store.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productRepository = productRepository;
    }

    @Override
    public OrderResponseDTO create(OrderRequestDTO orderRequestDTO, User user) {
        Product product = productRepository.findById(orderRequestDTO.getProductId())
                .orElseThrow(() -> new GeneralException("Product not found"));

        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setTotalPrice(product.getPrice());
        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderResponseDTO update(Long id, OrderRequestDTO orderRequestDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new GeneralException("Order not found"));
        Product product = productRepository.findById(orderRequestDTO.getProductId())
                        .orElseThrow(() -> new GeneralException("Product not found"));

        order.setProduct(product);
        order.setTotalPrice(product.getPrice());

        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO get(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new GeneralException("Order not found")));
    }

    @Override
    public List<OrderResponseDTO> getAll(Long id) {
        return orderRepository.findAllByUserId(id).stream().map(orderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
