package com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper;

import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.ProductEntity;

public class ProductEntityMapper {

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.id(),
                product.name(),
                product.price().amount(),
                product.stock().quantity()
        );
    }

    public static Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                new Money(entity.getPrice()),
                new Stock(entity.getStock())
        );
    }
}