package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.domain.model.Category;

import java.util.UUID;

public class UpdateCategoryService {

    private final CategoryRepository repository;

    public UpdateCategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID categoryId, String name, String description) {
        Category category = repository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Category updated = category.updateInfo(name, description);
        repository.save(updated);
    }
}