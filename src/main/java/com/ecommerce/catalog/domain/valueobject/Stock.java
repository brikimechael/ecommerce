package com.ecommerce.catalog.domain.valueobject;

public record Stock(int quantity) {

    public Stock {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
    }
}

