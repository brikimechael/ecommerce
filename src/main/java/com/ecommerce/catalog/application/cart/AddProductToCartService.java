package com.ecommerce.catalog.application.cart;


import com.ecommerce.catalog.application.port.in.AddProductToCart;
import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.domain.model.CartItem;
import com.ecommerce.catalog.domain.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AddProductToCartService implements AddProductToCart {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddProductToCartService(
            CartRepository cartRepository,
            ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void execute(UUID cartId, UUID productId, int quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        List<CartItem> updatedItems = new ArrayList<>(cart.items());

        boolean updated = false;
        for (int i = 0; i < updatedItems.size(); i++) {
            CartItem item = updatedItems.get(i);
            if (item.product().id().equals(productId)) {
                updatedItems.set(
                        i,
                        new CartItem(product, item.quantity() + quantity)
                );
                updated = true;
                break;
            }
        }

        if (!updated) {
            updatedItems.add(new CartItem(product, quantity));
        }

        Cart updatedCart = new Cart(cart.id(), List.copyOf(updatedItems));
        cartRepository.save(updatedCart);
    }
}

