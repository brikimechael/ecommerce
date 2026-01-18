package com.ecommerce.catalog.domain.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record Cart(
        UUID id,
        List<CartItem> items
) {
    public BigDecimal total() {
        return items.stream()
                .map(CartItem::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

