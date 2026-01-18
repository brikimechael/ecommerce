package com.ecommerce.catalog.application.category;

import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.domain.model.Category;
import com.ecommerce.catalog.domain.model.Product;

import java.util.UUID;

public class LinkCategoryProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public LinkCategoryProductService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void execute(UUID categoryId, UUID productId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Category updated = category.addProduct(product);
        categoryRepository.save(updated);
    }
}