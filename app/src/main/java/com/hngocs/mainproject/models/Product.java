package com.hngocs.mainproject.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int cate_id;
    private String description;
    private int image_id;

    public Product() {
    }

    public Product(int id, String name, int quantity, double price, int cate_id, String description, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.cate_id = cate_id;
        this.description = description;
        this.image_id = image_id;
    }

    public Product(int id, String name, int quantity, double price, String description, int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.image_id = image_id;
    }

    public Product(int id, String name, int quantity, double price,int image_id) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.image_id = image_id;
    }

    @NonNull
    @Override
    public String toString() {
        return id+"\t"+name+"\t"+price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}

