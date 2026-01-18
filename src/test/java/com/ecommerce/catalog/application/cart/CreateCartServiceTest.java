package com.ecommerce.catalog.application.cart;


import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCartRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateCartServiceTest {

    @Test
    void should_create_and_persist_cart() {
        InMemoryCartRepository repository = new InMemoryCartRepository();
        CreateCartService service = new CreateCartService(repository);

        var cartId = service.execute();

        Cart savedCart = repository.findById(cartId)
                .orElseThrow(() -> new AssertionError("Cart was not saved"));

        assertEquals(cartId, savedCart.id());
        assertTrue(savedCart.items().isEmpty());
    }
}

