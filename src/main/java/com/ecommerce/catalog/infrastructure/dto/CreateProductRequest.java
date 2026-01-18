package com.ecommerce.catalog.infrastructure.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductRequest(
        @NotBlank String name,
        @NotNull java.math.BigDecimal price,
        int stock
) {}
