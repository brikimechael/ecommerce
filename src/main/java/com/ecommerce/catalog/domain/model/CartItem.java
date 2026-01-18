package com.ecommerce.catalog.domain.model;


import java.math.BigDecimal;

public record CartItem(
        Product product,
        int quantity
) {
    public CartItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be > 0");
        }
    }

    public BigDecimal totalPrice() {
        return product.price().amount()
                .multiply(BigDecimal.valueOf(quantity));
    }
}

