package com.onlineshop.service;

import com.onlineshop.datastore.DataStore;
import com.onlineshop.entity.*;

import java.util.*;

public class OrderService {
    private final Map<Integer, Order> orders = DataStore.orders;

    public Order createOrder(Customer customer, List<ProductQuantityPair> items) {
        int id = DataStore.nextOrderId();
        Order order = new Order(id, customer);
        order.getProducts().addAll(items);
        orders.put(id, order);
        customer.getOrders().add(order);
        return order;
    }

    public Order getOrder(int orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void updateStatus(Order order, OrderStatus newStatus) {
        OrderStatus old = order.getStatus();
        if (old == newStatus) return;

        if (newStatus == OrderStatus.Cancelled && old != OrderStatus.Cancelled) {
            for (ProductQuantityPair pqp : order.getProducts()) {
                Product p = pqp.getProduct();
                p.setStockQuantity(p.getStockQuantity() + pqp.getQuantity());
            }
        }
        order.setStatus(newStatus);
    }
}
