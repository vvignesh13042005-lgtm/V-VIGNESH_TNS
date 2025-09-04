package com.onlineshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderId;
    private final Customer customer;
    private final List<ProductQuantityPair> products = new ArrayList<>();
    private OrderStatus status = OrderStatus.Pending;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<ProductQuantityPair> getProducts() { return products; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
