package com.ecommerce.catalog.application.port.out;


import com.ecommerce.catalog.domain.model.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {
    Category save(Category category);
    Optional<Category> findById(UUID id);
    void delete(UUID id);
}

