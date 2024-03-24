package com.example.springmid.services;

import com.example.springmid.dto.OrderDTO;
import com.example.springmid.entities.Order;
import com.example.springmid.entities.OrderItem;
import com.example.springmid.mappers.OrderMapper;
import com.example.springmid.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        if (order.getOrderDate() == null){
            order.setOrderDate(new Date());
        }

        double totalPrice = calculateTotalPrice(order.getOrderItems());
        order.setOrderPrice(totalPrice);
        order.setOrderStatus("Pending");
        return orderRepository.save(order);
    }

    private double calculateTotalPrice(List<OrderItem> orderItems) {
        double totalPrice = 0;
        for (OrderItem item : orderItems) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }


    @Override
    public Order updateOrder(Long id, Order order) {
        Optional<Order> optionalExistingOrder = orderRepository.findById(id);

        if (optionalExistingOrder.isPresent()){
            Order existingOrder = optionalExistingOrder.get();

            existingOrder.setUser(order.getUser());
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setOrderItems(order.getOrderItems());
            existingOrder.setTotalPrice(order.getTotalPrice());
            existingOrder.setOrderStatus(order.getOrderStatus());

            return orderRepository.save(existingOrder);
        }else{
            return null;
        }
    }

    @Override
    public void deleteOrder(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + id);
        }
    }

    @Override
    public OrderDTO saveOrder(OrderDTO newOrder) {
        Order orderToSave = OrderMapper.INSTANCE.orderDTOToOrder(newOrder);

        Order savedOrder = orderRepository.save(orderToSave);

        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }
}
