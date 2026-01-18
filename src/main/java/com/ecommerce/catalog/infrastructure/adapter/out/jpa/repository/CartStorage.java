package com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository;

import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartStorage
        extends JpaRepository<CartEntity, UUID> {
}

