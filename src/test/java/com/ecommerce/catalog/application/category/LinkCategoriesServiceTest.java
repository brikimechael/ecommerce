package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LinkCategoriesServiceTest {

    @Test
    void should_link_subcategory_to_parent() {
        var repository = new InMemoryCategoryRepository();
        var service = new LinkCategoriesService(repository);

        Category parent = Category.empty(UUID.randomUUID(), "Electronics", "All electronics");
        Category subCategory = Category.empty(UUID.randomUUID(), "Laptops", "Laptop computers");

        repository.save(parent);
        repository.save(subCategory);

        service.execute(parent.id(), subCategory.id());

        Category updated = repository.findById(parent.id()).orElseThrow();
        assertEquals(1, updated.subCategories().size());
        assertTrue(updated.subCategories().contains(subCategory));
    }

    @Test
    void should_throw_exception_when_parent_not_found() {
        var repository = new InMemoryCategoryRepository();
        var service = new LinkCategoriesService(repository);

        Category subCategory = Category.empty(UUID.randomUUID(), "Laptops", "Laptop computers");
        repository.save(subCategory);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(UUID.randomUUID(), subCategory.id())
        );
    }

    @Test
    void should_throw_exception_when_subcategory_not_found() {
        var repository = new InMemoryCategoryRepository();
        var service = new LinkCategoriesService(repository);

        Category parent = Category.empty(UUID.randomUUID(), "Electronics", "All electronics");
        repository.save(parent);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(parent.id(), UUID.randomUUID())
        );
    }
}