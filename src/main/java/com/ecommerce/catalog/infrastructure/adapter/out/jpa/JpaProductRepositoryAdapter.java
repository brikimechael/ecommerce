package com.ecommerce.catalog.infrastructure.adapter.out.jpa;


import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.ProductEntity;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper.ProductEntityMapper;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository.ProductStorage;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final ProductStorage repository;

    public JpaProductRepositoryAdapter(ProductStorage repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductEntityMapper.toEntity(product);
        repository.save(entity);
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return repository.findById(id)
                .map(ProductEntityMapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}



