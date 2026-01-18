package com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "carts")
public class CartEntity {

    @Id
    private UUID id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItemEntity> items = new HashSet<>();

    protected CartEntity() {}

    public CartEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public Set<CartItemEntity> getItems() {
        return items;
    }
}

