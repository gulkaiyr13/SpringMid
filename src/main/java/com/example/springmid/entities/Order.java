package com.example.springmid.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    private double totalPrice;
    private String orderStatus;

    public void setOrderId(long l) {
    }

    public void setQuantity(int i) {
    }

    public void setUser(User user) {
    }

    public void setProduct(Product product) {
    }
}
