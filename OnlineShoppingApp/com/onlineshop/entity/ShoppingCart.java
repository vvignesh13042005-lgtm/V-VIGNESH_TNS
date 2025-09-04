package com.onlineshop.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();

    public Map<Product, Integer> getItems() { return items; }

    public void addItem(Product product, int quantity) {
        if (quantity <= 0) return;
        items.put(product, items.getOrDefault(product, 0) + quantity);
    }

    public void clear() { items.clear(); }
}
