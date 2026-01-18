package com.ecommerce.catalog.infrastructure.adapter.out.memory;


import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Product;

import java.util.*;

public class InMemoryProductRepository implements ProductRepository {

    private final Map<UUID, Product> store = new HashMap<>();

    @Override
    public Product save(Product product) {
        store.put(product.id(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }
}
