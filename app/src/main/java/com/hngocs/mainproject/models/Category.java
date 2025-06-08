package com.hngocs.mainproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {
    private int id;
    private String name;
    private int image_id;
    private ArrayList<Product>products;

    public Category() {
        products=new ArrayList<>();
    }

    public Category(int id, String name, int image_id) {
        this.id = id;
        this.name = name;
        this.image_id = image_id;
        products=new ArrayList<>();
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

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return id+"\t"+name;
    }
    public  void addProduct(Product p)
    {
        products.add(p);
    }
}
