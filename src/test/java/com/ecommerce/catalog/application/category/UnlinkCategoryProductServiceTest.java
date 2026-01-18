package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.domain.model.Product;
import com.ecommerce.catalog.domain.valueobject.Money;
import com.ecommerce.catalog.domain.valueobject.Stock;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UnlinkCategoryProductServiceTest {

    @Test
    void should_unlink_product_from_category() {
        var categoryRepo = new InMemoryCategoryRepository();
        var productRepo = new InMemoryProductRepository();
        var service = new UnlinkCategoryProductService(categoryRepo, productRepo);

        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new Money(new BigDecimal("1000")),
                new Stock(10)
        );
        productRepo.save(product);

        Category category = Category.empty(UUID.randomUUID(), "Electronics", "Devices");
        category = category.addProduct(product);
        categoryRepo.save(category);

        service.execute(category.id(), product.id());

        Category updated = categoryRepo.findById(category.id()).orElseThrow();
        assertTrue(updated.products().isEmpty());
    }
}