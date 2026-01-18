package com.ecommerce.catalog.domain.valueobject;

import java.math.BigDecimal;

public record Money(BigDecimal amount) {

    public Money {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
    }
}

