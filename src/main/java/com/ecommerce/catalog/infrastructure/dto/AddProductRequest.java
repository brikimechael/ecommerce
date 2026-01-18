package com.ecommerce.catalog.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddProductRequest(
        @NotNull UUID productId,
        @Min(1) int quantity
) {}
