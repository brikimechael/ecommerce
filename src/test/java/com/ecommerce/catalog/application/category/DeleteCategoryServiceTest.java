package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCategoryServiceTest {

    @Test
    void should_delete_category() {
        var repository = new InMemoryCategoryRepository();
        var service = new DeleteCategoryService(repository);

        Category category = Category.empty(UUID.randomUUID(), "Electronics", "Devices");
        repository.save(category);

        service.execute(category.id());

        assertTrue(repository.findById(category.id()).isEmpty());
    }

    @Test
    void should_throw_exception_when_category_not_found() {
        var repository = new InMemoryCategoryRepository();
        var service = new DeleteCategoryService(repository);

        assertThrows(IllegalArgumentException.class, () ->
                service.execute(UUID.randomUUID())
        );
    }
}