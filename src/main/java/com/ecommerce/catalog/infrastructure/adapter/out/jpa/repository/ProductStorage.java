package com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository;


import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductStorage
        extends JpaRepository<ProductEntity, UUID> {
}

