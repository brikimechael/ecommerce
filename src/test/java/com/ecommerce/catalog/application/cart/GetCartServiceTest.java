package com.ecommerce.catalog.application.cart;

import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.domain.model.CartItem;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCartRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetCartServiceTest {

    @Test
    void should_get_cart_with_total() {
        var repository = new InMemoryCartRepository();
        var service = new GetCartService(repository);

        Product product1 = new Product(
                UUID.randomUUID(),
                "Laptop",
                new Money(new BigDecimal("1000")),
                new Stock(10)
        );

        Product product2 = new Product(
                UUID.randomUUID(),
                "Mouse",
                new Money(new BigDecimal("50")),
                new Stock(20)
        );

        Cart cart = new Cart(
                UUID.randomUUID(),
                List.of(
                        new CartItem(product1, 2),
                        new CartItem(product2, 3)
                )
        );
        repository.save(cart);

        Cart retrieved = service.execute(cart.id());

        assertEquals(cart.id(), retrieved.id());
        assertEquals(2, retrieved.items().size());
        assertEquals(new BigDecimal("2150"), retrieved.total());
    }

    @Test
    void should_throw_exception_when_cart_not_found() {
        var repository = new InMemoryCartRepository();
        var service = new GetCartService(repository);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(UUID.randomUUID())
        );
    }
}