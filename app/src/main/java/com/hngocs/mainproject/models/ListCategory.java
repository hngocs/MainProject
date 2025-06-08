package com.hngocs.mainproject.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Category> categories;

    public ListCategory() {
        categories=new ArrayList<>();
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public void generate_sample_product_dataset()
    {
        Category c1 = new Category(110, "Soft Drink", 1);
        Product p1 = new Product(1, "Coca Cola", 100, 10.0, 200);
        Product p2 = new Product(2, "Pepsi", 120, 9.5, 180);
        Product p3 = new Product(3, "7Up", 90, 8.0, 150);
        Product p4 = new Product(4, "Fanta", 85, 8.5, 160);
        Product p5 = new Product(5, "Sprite", 95, 9.0, 170);
        c1.addProduct(p1); c1.addProduct(p2); c1.addProduct(p3); c1.addProduct(p4); c1.addProduct(p5);
        categories.add(c1);

        Category c2 = new Category(111, "Cake", 1);
        Product p6 = new Product(6, "Chocolate Cake", 30, 25.0, 300);
        Product p7 = new Product(7, "Cheesecake", 20, 28.0, 310);
        Product p8 = new Product(8, "Carrot Cake", 25, 22.0, 280);
        Product p9 = new Product(9, "Tiramisu", 15, 30.0, 330);
        Product p10 = new Product(10, "Pineapple Cake", 18, 26.5, 290);
        c2.addProduct(p6); c2.addProduct(p7); c2.addProduct(p8); c2.addProduct(p9); c2.addProduct(p10);
        categories.add(c2);

        Category c3 = new Category(112, "Fastfood", 1);
        Product p11 = new Product(11, "Burger", 50, 20.0, 400);
        Product p12 = new Product(12, "Fried Chicken", 60, 22.5, 420);
        Product p13 = new Product(13, "Pizza", 40, 30.0, 450);
        Product p14 = new Product(14, "Hotdog", 55, 18.0, 380);
        Product p15 = new Product(15, "French Fries", 70, 15.0, 390);
        c3.addProduct(p11); c3.addProduct(p12); c3.addProduct(p13); c3.addProduct(p14); c3.addProduct(p15);
        categories.add(c3);

        Category c4 = new Category(113, "Beer", 1);
        Product p16 = new Product(16, "Heineken", 100, 18.0, 500);
        Product p17 = new Product(17, "Tiger", 90, 17.0, 480);
        Product p18 = new Product(18, "Budweiser", 80, 19.0, 520);
        Product p19 = new Product(19, "Larue", 110, 15.0, 460);
        Product p20 = new Product(20, "Saigon Beer", 95, 16.0, 470);
        c4.addProduct(p16); c4.addProduct(p17); c4.addProduct(p18); c4.addProduct(p19); c4.addProduct(p20);
        categories.add(c4);

        Category c5 = new Category(114, "Seafood", 1);
        Product p21 = new Product(21, "Grilled Squid", 25, 45.0, 600);
        Product p22 = new Product(22, "Fried Shrimp", 30, 50.0, 620);
        Product p23 = new Product(23, "Oyster", 20, 40.0, 590);
        Product p24 = new Product(24, "Lobster", 10, 150.0, 800);
        Product p25 = new Product(25, "Fish Fillet", 35, 35.0, 580);
        c5.addProduct(p21); c5.addProduct(p22); c5.addProduct(p23); c5.addProduct(p24); c5.addProduct(p25);
        categories.add(c5);

        Category c6 = new Category(115, "Vegetarian", 1);
        Product p26 = new Product(26, "Tofu Soup", 40, 20.0, 300);
        Product p27 = new Product(27, "Stir-fried Vegetables", 45, 22.0, 320);
        Product p28 = new Product(28, "Veggie Burger", 30, 25.0, 330);
        Product p29 = new Product(29, "Vegetarian Pizza", 35, 28.0, 340);
        Product p30 = new Product(30, "Mushroom Hotpot", 25, 32.0, 360);
        c6.addProduct(p26); c6.addProduct(p27); c6.addProduct(p28); c6.addProduct(p29); c6.addProduct(p30);
        categories.add(c6);

        Category c7 = new Category(116, "Fruit", 1);
        Product p31 = new Product(31, "Apple", 100, 5.0, 150);
        Product p32 = new Product(32, "Banana", 120, 3.0, 140);
        Product p33 = new Product(33, "Mango", 80, 7.0, 160);
        Product p34 = new Product(34, "Orange", 90, 6.0, 155);
        Product p35 = new Product(35, "Grapes", 85, 8.0, 165);
        c7.addProduct(p31); c7.addProduct(p32); c7.addProduct(p33); c7.addProduct(p34); c7.addProduct(p35);
        categories.add(c7);

        Category c8 = new Category(117, "Snack", 1);
        Product p36 = new Product(36, "Potato Chips", 150, 12.0, 210);
        Product p37 = new Product(37, "Popcorn", 100, 10.0, 200);
        Product p38 = new Product(38, "Rice Crackers", 130, 11.5, 220);
        Product p39 = new Product(39, "Nuts Mix", 90, 13.0, 230);
        Product p40 = new Product(40, "Choco Bar", 140, 9.5, 190);
        c8.addProduct(p36); c8.addProduct(p37); c8.addProduct(p38); c8.addProduct(p39); c8.addProduct(p40);
        categories.add(c8);

        Category c9 = new Category(118, "Coffee", 1);
        Product p41 = new Product(41, "Espresso", 80, 15.0, 300);
        Product p42 = new Product(42, "Cappuccino", 75, 18.0, 320);
        Product p43 = new Product(43, "Latte", 85, 17.5, 310);
        Product p44 = new Product(44, "Black Coffee", 90, 12.0, 280);
        Product p45 = new Product(45, "Iced Coffee", 95, 14.0, 290);
        c9.addProduct(p41); c9.addProduct(p42); c9.addProduct(p43); c9.addProduct(p44); c9.addProduct(p45);
        categories.add(c9);

        Category c10 = new Category(119, "Ice Cream", 1);
        Product p46 = new Product(46, "Vanilla", 60, 20.0, 250);
        Product p47 = new Product(47, "Chocolate", 70, 21.0, 260);
        Product p48 = new Product(48, "Strawberry", 65, 19.5, 255);
        Product p49 = new Product(49, "Matcha", 55, 22.0, 265);
        Product p50 = new Product(50, "Mango Sorbet", 50, 18.0, 240);
        c10.addProduct(p46); c10.addProduct(p47); c10.addProduct(p48); c10.addProduct(p49); c10.addProduct(p50);
        categories.add(c10);
    }
}
