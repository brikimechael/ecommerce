package com.ecommerce.catalog.application.port.out;


import com.ecommerce.catalog.domain.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    void delete(UUID id);
}

