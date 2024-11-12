package com.example.r_zaragoza.UCTop10Sellers.model;

public class Seller {
    private int userId;
    private String userName;
    private int salesCount;

    public Seller(int userId, String userName, int salesCount) {
        this.userId = userId;
        this.userName = userName;
        this.salesCount = salesCount;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getSalesCount() {
        return salesCount;
    }
}
