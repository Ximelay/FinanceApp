package com.example.financeapp;

public class FinanceRecord {
    private String type;
    private double amount;

    public FinanceRecord(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}