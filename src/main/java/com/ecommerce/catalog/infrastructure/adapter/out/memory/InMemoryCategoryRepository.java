package com.ecommerce.catalog.infrastructure.adapter.out.memory;

import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.domain.model.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryCategoryRepository implements CategoryRepository {

    private final Map<UUID, Category> store = new HashMap<>();

    @Override
    public Category save(Category category) {
        store.put(category.id(), category);
        return category;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void delete(UUID id) {
        store.remove(id);
    }
}