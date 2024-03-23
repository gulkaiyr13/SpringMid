package com.example.springmid.dto;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private Long id;
    private Long userId;
    private Date orderDate;
    private List<OrderItemDTO> orderItems;
    private double totalPrice;
    private String orderStatus;

    public OrderDTO() {
    }

    public OrderDTO(Long id, Long userId, Date orderDate, List<OrderItemDTO> orderItems, double totalPrice, String orderStatus) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
