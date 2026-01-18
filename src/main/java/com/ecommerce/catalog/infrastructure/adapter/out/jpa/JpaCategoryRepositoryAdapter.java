package com.ecommerce.catalog.infrastructure.adapter.out.jpa;

import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.entity.CategoryEntity;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.mapper.CategoryEntityMapper;
import com.ecommerce.catalog.infrastructure.adapter.out.jpa.repository.CategoryStorage;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryStorage categoryStorage;

    public JpaCategoryRepositoryAdapter(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = CategoryEntityMapper.toEntity(category);
        categoryStorage.save(entity);
        return category;
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryStorage.findById(id)
                .map(CategoryEntityMapper::toDomain);
    }

    @Override
    public void delete(UUID id) {
        categoryStorage.deleteById(id);
    }
}