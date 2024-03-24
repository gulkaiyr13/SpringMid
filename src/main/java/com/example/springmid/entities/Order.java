package com.example.springmid.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table (name="orders")
public class Order {
    @Getter
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
    private int quantity;


    public void setOrderPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public void setOrderId(long id) {
        this.id=id;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }


}
