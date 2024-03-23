package com.example.springmid.services;

import com.example.springmid.dto.OrderDTO;
import com.example.springmid.entities.Order;
import com.example.springmid.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Order getOrderById(Long id);

    Order createOrder(Order order);

    Order updateOrder(Long id, Order order);

    void deleteOrder(Long id);

    OrderDTO saveOrder(OrderDTO newOrder);
}
