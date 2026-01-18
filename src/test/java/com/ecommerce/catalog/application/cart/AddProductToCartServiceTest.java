package com.ecommerce.catalog.application.cart;


import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCartRepository;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddProductToCartServiceTest {

    @Test
    void should_add_and_update_product_in_cart() {
        var cartRepo = new InMemoryCartRepository();
        var productRepo = new InMemoryProductRepository();

        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new Money(new BigDecimal("1000")),
                new Stock(10)
        );

        productRepo.save(product);

        Cart cart = new Cart(UUID.randomUUID(), List.of());
        cartRepo.save(cart);

        AddProductToCartService service =
                new AddProductToCartService(cartRepo, productRepo);

        service.execute(cart.id(), product.id(), 2);
        service.execute(cart.id(), product.id(), 1);

        Cart updated = cartRepo.findById(cart.id()).orElseThrow();

        assertEquals(1, updated.items().size());
        assertEquals(3, updated.items().get(0).quantity());
        assertEquals(new BigDecimal("3000"), updated.total());
    }
}
