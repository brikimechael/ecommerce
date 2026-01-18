package com.ecommerce.catalog.infrastructure.adapter.in.web;

import com.ecommerce.catalog.application.product.CreateProductService;
import com.ecommerce.catalog.application.product.GetProductService;
import com.ecommerce.catalog.infrastructure.dto.CreateProductRequest;
import com.ecommerce.catalog.infrastructure.dto.ProductDTO;
import com.ecommerce.catalog.infrastructure.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductService createProductService;
    private final GetProductService getProductService;

    public ProductController(
            CreateProductService createProductService,
            GetProductService getProductService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody @Valid CreateProductRequest request) {
        return createProductService.execute(
                request.name(),
                request.price(),
                request.stock()
        );
    }

    @GetMapping("/{id}")
    public ProductDTO get(@PathVariable UUID id) {
        return ProductMapper.toDTO(getProductService.execute(id));
    }
}