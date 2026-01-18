package com.ecommerce.catalog.application.product;

import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Product;

import java.util.UUID;

public class GetProductService {

    private final ProductRepository repository;

    public GetProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product execute(UUID productId) {
        return repository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}