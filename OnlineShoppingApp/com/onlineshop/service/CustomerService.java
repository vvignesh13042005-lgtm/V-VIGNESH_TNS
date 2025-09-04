package com.onlineshop.service;

import com.onlineshop.datastore.DataStore;
import com.onlineshop.entity.*;

import java.util.*;

public class CustomerService {
    private final Map<Integer, Customer> customers = DataStore.customers;
    private final ProductService productService;
    private final OrderService orderService;

    public CustomerService(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
    }

    public boolean createCustomer(Customer c) {
        if (customers.containsKey(c.getUserId())) return false;
        customers.put(c.getUserId(), c);
        return true;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomer(int id) { return customers.get(id); }

    public List<Product> viewProducts() { return productService.getAllProducts(); }

    public Order placeOrder(int customerId, Map<Integer, Integer> productIdQty) {
        Customer customer = customers.get(customerId);
        if (customer == null) return null;

        for (Map.Entry<Integer, Integer> e : productIdQty.entrySet()) {
            Product p = productService.getProduct(e.getKey());
            int qty = e.getValue();
            if (p == null || qty <= 0 || p.getStockQuantity() < qty) {
                return null;
            }
        }

        List<ProductQuantityPair> items = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : productIdQty.entrySet()) {
            Product p = productService.getProduct(e.getKey());
            int qty = e.getValue();
            p.setStockQuantity(p.getStockQuantity() - qty);
            items.add(new ProductQuantityPair(p, qty));
        }

        return orderService.createOrder(customer, items);
    }

    public List<Order> listOrdersForCustomer(int customerId) {
        Customer c = customers.get(customerId);
        if (c == null) return Collections.emptyList();
        return c.getOrders();
    }
}
