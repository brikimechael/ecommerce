package com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper;

import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.domain.model.CartItem;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CartEntity;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CartItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CartEntityMapper {

    public static CartEntity toEntity(Cart cart) {
        CartEntity entity = new CartEntity(cart.id());

        cart.items().forEach(item -> {
            CartItemEntity itemEntity = new CartItemEntity(
                    item.product().id(),
                    item.quantity()
            );
            entity.getItems().add(itemEntity);
        });

        return entity;
    }

    public static Cart toDomain(CartEntity entity, ProductRepository productRepository) {
        List<CartItem> items = entity.getItems().stream()
                .map(itemEntity -> {
                    Product product = productRepository.findById(itemEntity.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "Product not found: " + itemEntity.getProductId()));
                    return new CartItem(product, itemEntity.getQuantity());
                })
                .collect(Collectors.toList());

        return new Cart(entity.getId(), items);
    }
}