package com.onlineshop.service;

import com.onlineshop.datastore.DataStore;
import com.onlineshop.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Map<Integer, Product> products = DataStore.products;

    public boolean addProduct(Product p) {
        if (products.containsKey(p.getProductId())) return false;
        products.put(p.getProductId(), p);
        return true;
    }

    public boolean removeProduct(int productId) {
        return products.remove(productId) != null;
    }

    public Product getProduct(int id) {
        return products.get(id);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
