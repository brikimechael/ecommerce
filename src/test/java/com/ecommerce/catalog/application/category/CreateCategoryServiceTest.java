package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCategoryServiceTest {

    @Test
    void should_create_and_persist_category() {
        InMemoryCategoryRepository repository = new InMemoryCategoryRepository();
        CreateCategoryService service = new CreateCategoryService(repository);

        var categoryId = service.execute("Electronics", "Electronic devices");

        Category saved = repository.findById(categoryId)
                .orElseThrow(() -> new AssertionError("Category was not saved"));

        assertEquals(categoryId, saved.id());
        assertEquals("Electronics", saved.name());
        assertEquals("Electronic devices", saved.description());
        assertTrue(saved.products().isEmpty());
        assertTrue(saved.subCategories().isEmpty());
    }
}