package com.nidhallourimi.core.data;

import com.nidhallourimi.command.OrderStatus;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(unique = true)
    public String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
