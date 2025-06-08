package com.hngocs.mainproject.models;

public class OrderDetails {
    private int Id;
    private int OrderId;
    private int ProductId;
    private int Quantity;
    private double Price;
    private double Discount;
    private double VAT;
    private double TotalValue;

    public OrderDetails() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double discount) {
        Discount = discount;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getTotalValue() {
        TotalValue=(Quantity*Price-Discount/100*Quantity*Price)*(1+VAT/100);
        return TotalValue;
    }
}
