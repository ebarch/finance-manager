package com.ebarch.financeManager.domain;

import com.ebarch.financeManager.util.TransactionTypeEnum;
import java.util.Calendar;

/**
 *
 * @author Ed
 */
public class Transaction {
    
    private int id;    
    private String description;
    private Calendar date;
    private TransactionTypeEnum type;
    private double amount;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionTypeEnum getType() {
        return type;
    }

    public void setType(TransactionTypeEnum type) {
        this.type = type;
    } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
