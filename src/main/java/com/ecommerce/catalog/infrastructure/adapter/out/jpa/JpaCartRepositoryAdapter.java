package com.ecommerce.catalog.infrastructure.adapter.out.jpa;

import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Cart;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CartEntity;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper.CartEntityMapper;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository.CartStorage;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCartRepositoryAdapter implements CartRepository {

    private final CartStorage cartStorage;
    private final ProductRepository productRepository;

    public JpaCartRepositoryAdapter(CartStorage cartStorage, ProductRepository productRepository) {
        this.cartStorage = cartStorage;
        this.productRepository = productRepository;
    }

    @Override
    public Cart save(Cart cart) {
        CartEntity entity = CartEntityMapper.toEntity(cart);
        cartStorage.save(entity);
        return cart;
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return cartStorage.findById(id)
                .map(entity -> CartEntityMapper.toDomain(entity, productRepository));
    }
}