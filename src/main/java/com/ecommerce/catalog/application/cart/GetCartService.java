package com.ecommerce.catalog.application.cart;

import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.domain.model.Cart;

import java.util.UUID;

public class GetCartService {

    private final CartRepository repository;

    public GetCartService(CartRepository repository) {
        this.repository = repository;
    }

    public Cart execute(UUID cartId) {
        return repository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
    }
}