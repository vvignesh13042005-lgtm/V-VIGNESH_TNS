package com.onlineshop.main;

import com.onlineshop.entity.*;
import com.onlineshop.service.*;

import java.util.*;

public class ShoppingApp {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        AdminService adminService = new AdminService(productService, orderService);
        CustomerService customerService = new CustomerService(productService, orderService);

        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = readInt();

            switch (mainChoice) {
                case 1: adminMenu(adminService); break;
                case 2: customerMenu(customerService); break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void adminMenu(AdminService adminService) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Products");
            System.out.println("4. Create Admin");
            System.out.println("5. View Admins");
            System.out.println("6. Update Order Status");
            System.out.println("7. View Orders");
            System.out.println("8. Return");
            System.out.print("Choose an option: ");
            int ch = readInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int pid = readInt();
                    System.out.print("Enter Product Name: ");
                    String pname = readLine();
                    System.out.print("Enter Product Price: ");
                    double price = readDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int qty = readInt();
                    boolean added = adminService.addProduct(new Product(pid, pname, price, qty));
                    System.out.println(added ? "Product added successfully!" : "Product ID already exists!");
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int rid = readInt();
                    boolean removed = adminService.removeProduct(rid);
                    System.out.println(removed ? "Product removed successfully!" : "Product not found!");
                    break;
                case 3:
                    System.out.println("Products:");
                    adminService.viewProducts().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter Admin User ID: ");
                    int aid = readInt();
                    System.out.print("Enter Username: ");
                    String aun = readLine();
                    System.out.print("Enter Email: ");
                    String aem = readLine();
                    boolean created = adminService.createAdmin(new Admin(aid, aun, aem));
                    System.out.println(created ? "Admin created successfully!" : "Admin ID already exists!");
                    break;
                case 5:
                    System.out.println("Admins:");
                    adminService.getAllAdmins().forEach(a -> System.out.println(a.toString()));
                    break;
                case 6:
                    System.out.print("Enter Order ID: ");
                    int oid = readInt();
                    System.out.print("Enter new status (Completed/Delivered/Cancelled): ");
                    String st = readLine();
                    boolean ok = adminService.updateOrderStatus(oid, st);
                    System.out.println(ok ? "Order status updated." : "Invalid order or status.");
                    break;
                case 7:
                    System.out.println("Orders:");
                    for (Order o : adminService.viewOrders()) {
                        System.out.println("Order ID: " + o.getOrderId() + ", Customer: " + o.getCustomer().getUsername()
                                + ", Status: " + o.getStatus());
                        for (ProductQuantityPair pqp : o.getProducts()) {
                            System.out.println("  Product: " + pqp.getProduct().getName() + ", Quantity: " + pqp.getQuantity());
                        }
                    }
                    break;
                case 8:
                    System.out.println("Exiting Admin...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void customerMenu(CustomerService customerService) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Create Customer");
            System.out.println("2. View Customers");
            System.out.println("3. Place Order");
            System.out.println("4. View Orders");
            System.out.println("5. View Products");
            System.out.println("6. Return");
            System.out.print("Choose an option: ");
            int ch = readInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int uid = readInt();
                    System.out.print("Enter Username: ");
                    String un = readLine();
                    System.out.print("Enter Email: ");
                    String em = readLine();
                    System.out.print("Enter Address: ");
                    String addr = readLine();
                    boolean created = customerService.createCustomer(new Customer(uid, un, em, addr));
                    System.out.println(created ? "Customer created successfully!" : "Customer ID already exists!");
                    break;
                case 2:
                    System.out.println("Customers:");
                    customerService.getAllCustomers().forEach(c -> System.out.println(c.toString()));
                    break;
                case 3:
                    System.out.print("Enter Customer ID: ");
                    int cid = readInt();
                    Map<Integer, Integer> cart = new LinkedHashMap<>();
                    while (true) {
                        System.out.print("Enter Product ID to add to order (or -1 to complete): ");
                        int pid = readInt();
                        if (pid == -1) break;
                        System.out.print("Enter quantity: ");
                        int q = readInt();
                        cart.put(pid, cart.getOrDefault(pid, 0) + q);
                    }
                    Order order = customerService.placeOrder(cid, cart);
                    System.out.println(order != null ? "Order placed successfully!" : "Order failed (invalid product/quantity/stock).");
                    break;
                case 4:
                    System.out.print("Enter Customer ID: ");
                    int ocid = readInt();
                    List<Order> orders = customerService.listOrdersForCustomer(ocid);
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        System.out.println("Orders:");
                        for (Order o : orders) {
                            System.out.println("Order ID: " + o.getOrderId() + ", Status: " + o.getStatus());
                            for (ProductQuantityPair pqp : o.getProducts()) {
                                System.out.println("  Product: " + pqp.getProduct().getName() +
                                        ", Quantity: " + pqp.getQuantity());
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Products:");
                    customerService.viewProducts().forEach(System.out::println);
                    break;
                case 6:
                    System.out.println("Exiting Customer Menu...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static int readInt() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Enter a valid integer: ");
            }
        }
    }

    private static double readDouble() {
        while (true) {
            try {
                String s = sc.nextLine().trim();
                return Double.parseDouble(s);
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private static String readLine() {
        String s = sc.nextLine();
        while (s.trim().isEmpty()) s = sc.nextLine();
        return s.trim();
    }
}
