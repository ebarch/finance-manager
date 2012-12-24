/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebarch.financeManager.domain;

/**
 *
 * @author Ed
 */
public class Category {
    
    private int id;
    
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
