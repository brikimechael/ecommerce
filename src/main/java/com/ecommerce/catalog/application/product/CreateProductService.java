package com.ecommerce.catalog.application.product;

import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateProductService {

    private final ProductRepository repository;

    public CreateProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public UUID execute(String name, BigDecimal price, int stock) {
        Product product = new Product(
                UUID.randomUUID(),
                name,
                new Money(price),
                new Stock(stock)
        );

        return repository.save(product).id();
    }
}