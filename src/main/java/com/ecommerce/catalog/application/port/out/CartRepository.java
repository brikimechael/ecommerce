package com.ecommerce.catalog.application.port.out;


import com.ecommerce.catalog.domain.model.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {
    Cart save(Cart cart);
    Optional<Cart> findById(UUID id);
}

