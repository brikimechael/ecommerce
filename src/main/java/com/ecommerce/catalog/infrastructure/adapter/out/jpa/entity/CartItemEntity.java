package com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cart_items")
public class CartItemEntity {

    @Id
    @GeneratedValue
    private Long id;

    private UUID productId;
    private int quantity;

    protected CartItemEntity() {}

    public CartItemEntity(UUID productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}

