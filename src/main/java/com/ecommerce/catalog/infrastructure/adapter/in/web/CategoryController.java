package com.ecommerce.catalog.infrastructure.adapter.in.web;

import com.ecommerce.catalog.application.category.*;
import com.ecommerce.catalog.infrastructure.dto.CreateCategoryRequest;
import com.ecommerce.catalog.infrastructure.dto.UpdateCategoryRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CreateCategoryService createCategory;
    private final UpdateCategoryService updateCategory;
    private final DeleteCategoryService deleteCategory;
    private final LinkCategoryProductService linkCategoryProduct;
    private final UnlinkCategoryProductService unlinkCategoryProduct;
    private final LinkCategoriesService linkCategories;
    private final UnlinkCategoriesService unlinkCategories;

    public CategoryController(
            CreateCategoryService createCategory,
            UpdateCategoryService updateCategory,
            DeleteCategoryService deleteCategory,
            LinkCategoryProductService linkCategoryProduct,
            UnlinkCategoryProductService unlinkCategoryProduct,
            LinkCategoriesService linkCategories,
            UnlinkCategoriesService unlinkCategories) {
        this.createCategory = createCategory;
        this.updateCategory = updateCategory;
        this.deleteCategory = deleteCategory;
        this.linkCategoryProduct = linkCategoryProduct;
        this.unlinkCategoryProduct = unlinkCategoryProduct;
        this.linkCategories = linkCategories;
        this.unlinkCategories = unlinkCategories;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody @Valid CreateCategoryRequest request) {
        return createCategory.execute(request.name(), request.description());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateCategoryRequest request) {
        updateCategory.execute(id, request.name(), request.description());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        deleteCategory.execute(id);
    }

    @PostMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkProduct(
            @PathVariable UUID categoryId,
            @PathVariable UUID productId) {
        linkCategoryProduct.execute(categoryId, productId);
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkProduct(
            @PathVariable UUID categoryId,
            @PathVariable UUID productId) {
        unlinkCategoryProduct.execute(categoryId, productId);
    }

    @PostMapping("/{parentId}/subcategories/{subCategoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void linkSubCategory(
            @PathVariable UUID parentId,
            @PathVariable UUID subCategoryId) {
        linkCategories.execute(parentId, subCategoryId);
    }

    @DeleteMapping("/{parentId}/subcategories/{subCategoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlinkSubCategory(
            @PathVariable UUID parentId,
            @PathVariable UUID subCategoryId) {
        unlinkCategories.execute(parentId, subCategoryId);
    }
}