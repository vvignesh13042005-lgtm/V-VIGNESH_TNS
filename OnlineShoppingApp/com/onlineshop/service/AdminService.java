package com.onlineshop.service;

import com.onlineshop.datastore.DataStore;
import com.onlineshop.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminService {
    private final Map<Integer, Admin> admins = DataStore.admins;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public boolean createAdmin(Admin admin) {
        if (admins.containsKey(admin.getUserId())) return false;
        admins.put(admin.getUserId(), admin);
        return true;
    }

    public List<Admin> getAllAdmins() {
        return new ArrayList<>(admins.values());
    }

    public boolean addProduct(Product p) { return productService.addProduct(p); }
    public boolean removeProduct(int productId) { return productService.removeProduct(productId); }
    public List<Product> viewProducts() { return productService.getAllProducts(); }

    public boolean updateOrderStatus(int orderId, String statusText) {
        Order order = orderService.getOrder(orderId);
        if (order == null) return false;
        try {
            OrderStatus status = OrderStatus.valueOf(statusText);
            orderService.updateStatus(order, status);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Order> viewOrders() { return orderService.getAllOrders(); }
}
