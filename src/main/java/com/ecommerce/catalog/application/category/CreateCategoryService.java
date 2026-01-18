package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.application.port.in.CreateCategory;
import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.domain.model.Category;

import java.util.UUID;

public class CreateCategoryService implements CreateCategory {

    private final CategoryRepository repository;

    public CreateCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID execute(String name, String description) {
        Category category = Category.empty(UUID.randomUUID(), name, description);
        return repository.save(category).id();
    }
}