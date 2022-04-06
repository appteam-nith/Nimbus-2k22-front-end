package com.nith.nimbus2k22.Models;

public class StoreList {
    String id;
    String productName;
    int price;
    String description;
    String payment;
    String department;

    public StoreList(String id, String productName, int price, String description, String payment, String department) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.payment = payment;
        this.department = department;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
