package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCategoryServiceTest {

    @Test
    void should_update_category() {
        var repository = new InMemoryCategoryRepository();
        var service = new UpdateCategoryService(repository);

        Category category = Category.empty(UUID.randomUUID(), "Old Name", "Old Desc");
        repository.save(category);

        service.execute(category.id(), "New Name", "New Description");

        Category updated = repository.findById(category.id()).orElseThrow();
        assertEquals("New Name", updated.name());
        assertEquals("New Description", updated.description());
    }

    @Test
    void should_throw_exception_when_category_not_found() {
        var repository = new InMemoryCategoryRepository();
        var service = new UpdateCategoryService(repository);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(UUID.randomUUID(), "Name", "Desc")
        );
    }
}