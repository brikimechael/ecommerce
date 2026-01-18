package com.ecommerce.catalog.application.port.in;

import java.util.UUID;

public interface AddProductToCart {
    void execute(UUID cartId, UUID productId, int quantity);
}

