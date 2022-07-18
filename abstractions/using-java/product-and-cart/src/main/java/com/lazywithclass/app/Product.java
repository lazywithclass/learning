package com.lazywithclass.app;

import java.util.HashSet;
import java.util.Set;

class Product {

    // TODO implement equals, do I also have to implement hashcode, lookup into effective code

    private String description;
    private float price;

    // this represents the constraint that does not allow two Product
    // to have the same description
    private static Set<String> seenDescriptions;

    public Product(String description, float price) {
        // TODO Check null
        if (description.isEmpty()) {
            throw new IllegalStateException("description should not be empty");
        }
        if (price <= 0) {
            throw new IllegalStateException("price should be positive");
        }

        if (seenDescriptions == null) {
            seenDescriptions = new HashSet<>();
        }

        if (seenDescriptions.contains(description)) {
            throw new IllegalStateException("cannot have two products with the same description");
        }

        this.description = description;
        seenDescriptions.add(description);
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }
}
