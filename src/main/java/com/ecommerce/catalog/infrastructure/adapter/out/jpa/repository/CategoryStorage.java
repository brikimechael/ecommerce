package com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository;

import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryStorage
        extends JpaRepository<CategoryEntity, UUID> {
}
