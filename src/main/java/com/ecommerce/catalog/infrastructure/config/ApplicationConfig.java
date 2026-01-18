package com.ecommerce.catalog.infrastructure.config;


import com.ecommerce.catalog.application.cart.AddProductToCartService;
import com.ecommerce.catalog.application.cart.CreateCartService;
import com.ecommerce.catalog.application.cart.GetCartService;
import com.ecommerce.catalog.application.category.*;
import com.ecommerce.catalog.application.port.out.CartRepository;
import com.ecommerce.catalog.application.port.out.CategoryRepository;
import com.ecommerce.catalog.application.port.out.ProductRepository;
import com.ecommerce.catalog.application.product.CreateProductService;
import com.ecommerce.catalog.application.product.GetProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateCategoryService createCategoryService(CategoryRepository categoryRepository) {
        return new CreateCategoryService(categoryRepository);
    }

    @Bean
    public CreateProductService createProductService(ProductRepository productRepository) {
        return new CreateProductService(productRepository);
    }

    @Bean
    public GetProductService getProductService(ProductRepository productRepository) {
        return new GetProductService(productRepository);
    }

    @Bean
    public UpdateCategoryService updateCategoryService(CategoryRepository categoryRepository) {
        return new UpdateCategoryService(categoryRepository);
    }

    @Bean
    public DeleteCategoryService deleteCategoryService(CategoryRepository categoryRepository) {
        return new DeleteCategoryService(categoryRepository);
    }

    @Bean
    public LinkCategoryProductService linkCategoryProductService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        return new LinkCategoryProductService(categoryRepository, productRepository);
    }

    @Bean
    public UnlinkCategoryProductService unlinkCategoryProductService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository) {
        return new UnlinkCategoryProductService(categoryRepository, productRepository);
    }

    @Bean
    public LinkCategoriesService linkCategoriesService(CategoryRepository categoryRepository) {
        return new LinkCategoriesService(categoryRepository);
    }

    @Bean
    public UnlinkCategoriesService unlinkCategoriesService(CategoryRepository categoryRepository) {
        return new UnlinkCategoriesService(categoryRepository);
    }

    @Bean
    public CreateCartService createCartService(CartRepository cartRepository) {
        return new CreateCartService(cartRepository);
    }

    @Bean
    public AddProductToCartService addProductToCartService(
            CartRepository cartRepository,
            ProductRepository productRepository) {
        return new AddProductToCartService(cartRepository, productRepository);
    }

    @Bean
    public GetCartService getCartService(CartRepository cartRepository) {
        return new GetCartService(cartRepository);
    }
}