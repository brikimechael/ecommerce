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

class LinkCategoryProductServiceTest {

    @Test
    void should_link_product_to_category() {
        var categoryRepo = new InMemoryCategoryRepository();
        var productRepo = new InMemoryProductRepository();
        var service = new LinkCategoryProductService(categoryRepo, productRepo);

        Category category = Category.empty(UUID.randomUUID(), "Electronics", "Devices");
        categoryRepo.save(category);

        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new Money(new BigDecimal("1000")),
                new Stock(10)
        );
        productRepo.save(product);

        service.execute(category.id(), product.id());

        Category updated = categoryRepo.findById(category.id()).orElseThrow();
        assertEquals(1, updated.products().size());
        assertTrue(updated.products().contains(product));
    }

    @Test
    void should_throw_exception_when_category_not_found() {
        var categoryRepo = new InMemoryCategoryRepository();
        var productRepo = new InMemoryProductRepository();
        var service = new LinkCategoryProductService(categoryRepo, productRepo);

        Product product = new Product(
                UUID.randomUUID(),
                "Laptop",
                new Money(new BigDecimal("1000")),
                new Stock(10)
        );
        productRepo.save(product);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(UUID.randomUUID(), product.id())
        );
    }

    @Test
    void should_throw_exception_when_product_not_found() {
        var categoryRepo = new InMemoryCategoryRepository();
        var productRepo = new InMemoryProductRepository();
        var service = new LinkCategoryProductService(categoryRepo, productRepo);

        Category category = Category.empty(UUID.randomUUID(), "Electronics", "Devices");
        categoryRepo.save(category);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(category.id(), UUID.randomUUID())
        );
    }
}