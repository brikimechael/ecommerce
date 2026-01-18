package com.ecommerce.catalog.infrastructure.dto;

import java.util.UUID;

public record CartItemDTO(
        UUID productId,
        int quantity
) {}
