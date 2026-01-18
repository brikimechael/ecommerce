package com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    private UUID id;

    private String name;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_products",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<ProductEntity> products = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_subcategories",
            joinColumns = @JoinColumn(name = "parent_category_id"),
            inverseJoinColumns = @JoinColumn(name = "sub_category_id")
    )
    private Set<CategoryEntity> subCategories = new HashSet<>();

    protected CategoryEntity() {}

    public CategoryEntity(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }

    public Set<CategoryEntity> getSubCategories() {
        return subCategories;
    }
}