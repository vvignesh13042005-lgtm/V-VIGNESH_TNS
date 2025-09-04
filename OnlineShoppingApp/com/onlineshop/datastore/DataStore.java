package com.onlineshop.datastore;

import com.onlineshop.entity.*;

import java.util.*;

public class DataStore {
    public static final Map<Integer, Product> products = new LinkedHashMap<>();
    public static final Map<Integer, Customer> customers = new LinkedHashMap<>();
    public static final Map<Integer, Admin> admins = new LinkedHashMap<>();
    public static final Map<Integer, Order> orders = new LinkedHashMap<>();

    private static int orderSeq = 1;

    public static int nextOrderId() { return orderSeq++; }
}
