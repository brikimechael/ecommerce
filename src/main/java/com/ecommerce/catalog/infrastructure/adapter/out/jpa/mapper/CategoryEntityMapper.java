package com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CategoryEntity;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.ProductEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CategoryEntityMapper {

    public static CategoryEntity toEntity(Category category) {
        CategoryEntity entity = new CategoryEntity(
                category.id(),
                category.name(),
                category.description()
        );

        Set<ProductEntity> productEntities = category.products().stream()
                .map(ProductEntityMapper::toEntity)
                .collect(Collectors.toSet());
        entity.getProducts().clear();
        entity.getProducts().addAll(productEntities);

        Set<CategoryEntity> subCategoryEntities = category.subCategories().stream()
                .map(CategoryEntityMapper::toEntity)
                .collect(Collectors.toSet());
        entity.getSubCategories().clear();
        entity.getSubCategories().addAll(subCategoryEntities);

        return entity;
    }

    public static Category toDomain(CategoryEntity entity) {
        return toDomainRecursive(entity, new HashSet<>());
    }

    private static Category toDomainRecursive(CategoryEntity entity, Set<UUID> visitedIds) {
        if (visitedIds.contains(entity.getId())) {
            return Category.empty(entity.getId(), entity.getName(), entity.getDescription());
        }

        visitedIds.add(entity.getId());

        Set<Product> products = entity.getProducts().stream()
                .map(ProductEntityMapper::toDomain)
                .collect(Collectors.toSet());

        Set<Category> subCategories = entity.getSubCategories().stream()
                .map(subEntity -> toDomainRecursive(subEntity, new HashSet<>(visitedIds)))
                .collect(Collectors.toSet());

        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                subCategories,
                products
        );
    }
}