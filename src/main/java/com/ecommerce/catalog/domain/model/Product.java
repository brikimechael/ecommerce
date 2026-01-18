package com.ecommerce.catalog.domain.model;


import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;

import java.util.UUID;

public record Product(
        UUID id,
        String name,
        Money price,
        Stock stock
) {
    public Product {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }
    }
}

