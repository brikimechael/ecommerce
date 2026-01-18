package com.ecommerce.catalog.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public record Category(
        UUID id,
        String name,
        String description,
        Set<Category> subCategories,
        Set<Product> products
) {
    public Category {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name is required");
        }
        subCategories = new HashSet<>(subCategories);
        products = new HashSet<>(products);
    }

    public static Category empty(UUID id, String name, String description) {
        return new Category(id, name, description, Set.of(), Set.of());
    }

    public Category addSubCategory(Category category) {
        Set<Category> updated = new HashSet<>(this.subCategories);
        updated.add(category);
        return new Category(this.id, this.name, this.description, updated, this.products);
    }

    public Category addProduct(Product product) {
        Set<Product> updated = new HashSet<>(this.products);
        updated.add(product);
        return new Category(this.id, this.name, this.description, this.subCategories, updated);
    }

    public Category removeProduct(Product product) {
        Set<Product> updated = new HashSet<>(this.products);
        updated.remove(product);
        return new Category(this.id, this.name, this.description, this.subCategories, updated);
    }

    public Category removeSubCategory(Category category) {
        Set<Category> updated = new HashSet<>(this.subCategories);
        updated.remove(category);
        return new Category(this.id, this.name, this.description, updated, this.products);
    }

    public Category updateInfo(String name, String description) {
        return new Category(this.id, name, description, this.subCategories, this.products);
    }
}