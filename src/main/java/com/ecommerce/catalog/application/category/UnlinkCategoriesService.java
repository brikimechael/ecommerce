package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.domain.model.Category;

import java.util.UUID;

public class UnlinkCategoriesService {

    private final CategoryRepository repository;

    public UnlinkCategoriesService(CategoryRepository repository) {
        this.repository = repository;
    }

    public void execute(UUID parentCategoryId, UUID subCategoryId) {
        Category parent = repository.findById(parentCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Parent category not found"));

        Category subCategory = repository.findById(subCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("Sub-category not found"));

        Category updated = parent.removeSubCategory(subCategory);
        repository.save(updated);
    }
}