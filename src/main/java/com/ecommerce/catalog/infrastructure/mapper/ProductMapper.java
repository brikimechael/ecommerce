package com.ecommerce.catalog.infrastructure.mapper;


import com.ecommerce.catalog.infrastructure.dto.ProductDTO;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;

public class ProductMapper {

    public static Product toDomain(ProductDTO dto) {
        return new Product(
                dto.id(),
                dto.name(),
                new Money(dto.price()),
                new Stock(dto.stock())
        );
    }

    public static ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.id(),
                product.name(),
                product.price().amount(),
                product.stock().quantity()
        );
    }
}
