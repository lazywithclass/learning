package com.lazywithclass.app;

import java.util.ArrayList;
import java.util.List;

class Cart {

    private float totalPrice;
    private List<Product> products;

    public Cart() {
        totalPrice = 0;
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
        totalPrice += product.getPrice();
    }

    public void remove(Product product) {
        products.remove(product);
        totalPrice -= product.getPrice();
    }

    public float totalPrice() {
        // by updating totalPrice whenever add and remove are called
        // we avoid to spend O(n) here by going through all products and
        // adding up their prices
        return totalPrice;
    }
}
