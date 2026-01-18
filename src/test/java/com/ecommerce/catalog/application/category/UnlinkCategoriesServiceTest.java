package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.memory.InMemoryCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UnlinkCategoriesServiceTest {

    @Test
    void should_unlink_subcategory_from_parent() {
        var repository = new InMemoryCategoryRepository();
        var service = new UnlinkCategoriesService(repository);

        Category subCategory = Category.empty(UUID.randomUUID(), "Laptops", "Laptop computers");
        repository.save(subCategory);

        Category parent = Category.empty(UUID.randomUUID(), "Electronics", "All electronics");
        parent = parent.addSubCategory(subCategory);
        repository.save(parent);

        service.execute(parent.id(), subCategory.id());

        Category updated = repository.findById(parent.id()).orElseThrow();
        assertTrue(updated.subCategories().isEmpty());
    }
}