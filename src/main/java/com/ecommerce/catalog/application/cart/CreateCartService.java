package com.ecommerce.catalog.application.cart;


import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.domain.model.Cart;

import java.util.List;
import java.util.UUID;

public class CreateCartService {

    private final CartRepository repository;

    public CreateCartService(CartRepository repository) {
        this.repository = repository;
    }

    public UUID execute() {
        Cart cart = new Cart(UUID.randomUUID(), List.of());
        repository.save(cart);
        return cart.id();
    }
}

