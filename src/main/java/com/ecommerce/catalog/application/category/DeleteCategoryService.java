package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.application.port.out.CategoryRepository;

import java.util.UUID;

public class DeleteCategoryService {

    private final CategoryRepository repository;

    public DeleteCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID categoryId) {
        if (repository.findById(categoryId).isEmpty()) {
            throw new IllegalArgumentException("Category not found");
        }
        repository.delete(categoryId);
    }
}