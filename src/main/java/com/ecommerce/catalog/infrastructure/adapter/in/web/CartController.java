package com.ecommerce.catalog.infrastructure.adapter.in.web;

import com.ecommerce.catalog.application.cart.AddProductToCartService;
import com.ecommerce.catalog.application.cart.CreateCartService;
import com.ecommerce.catalog.application.cart.GetCartService;
import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.infrastructure.dto.AddProductRequest;
import com.ecommerce.catalog.infrastructure.dto.CartDTO;
import com.ecommerce.catalog.infrastructure.mapper.CartMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CreateCartService createCart;
    private final AddProductToCartService addProductToCart;
    private final GetCartService getCart;

    public CartController(
            CreateCartService createCart,
            AddProductToCartService addProductToCart,
            GetCartService getCart) {
        this.createCart = createCart;
        this.addProductToCart = addProductToCart;
        this.getCart = getCart;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create() {
        return createCart.execute();
    }

    @GetMapping("/{cartId}")
    public CartDTO get(@PathVariable UUID cartId) {
        Cart cart = getCart.execute(cartId);
        return CartMapper.toDTO(cart);
    }

    @PostMapping("/{cartId}/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addProduct(
            @PathVariable UUID cartId,
            @RequestBody @Valid AddProductRequest request) {
        addProductToCart.execute(cartId, request.productId(), request.quantity());
    }
}