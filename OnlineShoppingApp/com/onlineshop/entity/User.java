package com.onlineshop.entity;

public abstract class User {
    private int userId;
    private String username;
    private String email;

    public User(int userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Email: " + email;
    }
}
