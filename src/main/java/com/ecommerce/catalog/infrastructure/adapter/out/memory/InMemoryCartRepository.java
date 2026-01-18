package com.ecommerce.catalog.infrastructure.adapter.out.memory;

import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.domain.model.Cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCartRepository implements CartRepository {

    private final Map<UUID, Cart> store = new HashMap<>();

    @Override
    public Cart save(Cart cart) {
        store.put(cart.id(), cart);
        return cart;
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }
}
