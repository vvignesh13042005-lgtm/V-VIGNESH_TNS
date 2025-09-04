package com.onlineshop.entity;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String address;
    private ShoppingCart shoppingCart = new ShoppingCart();
    private List<Order> orders = new ArrayList<>();

    public Customer(int userId, String username, String email, String address) {
        super(userId, username, email);
        this.address = address;
    }

    public String getAddress() { return address; }
    public ShoppingCart getShoppingCart() { return shoppingCart; }
    public List<Order> getOrders() { return orders; }

    @Override
    public String toString() {
        return super.toString() + ", Address: " + address;
    }
}
