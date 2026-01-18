package com.ecommerce.catalog.infrastructure.mapper;

import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.domain.model.CartItem;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.infrastructure.dto.CartDTO;
import com.ecommerce.catalog.infrastructure.dto.CartItemDTO;

import java.util.List;
import java.util.Map;

public class CartMapper {

    public static Cart toDomain(CartDTO dto, Map<Product, Integer> products) {
        List<CartItem> items = products.entrySet().stream()
                .map(e -> new CartItem(e.getKey(), e.getValue()))
                .toList();

        return new Cart(dto.id(), items);
    }

    public static CartDTO toDTO(Cart cart) {
        List<CartItemDTO> items = cart.items().stream()
                .map(i -> new CartItemDTO(i.product().id(), i.quantity()))
                .toList();

        return new CartDTO(cart.id(), items, cart.total());
    }
}