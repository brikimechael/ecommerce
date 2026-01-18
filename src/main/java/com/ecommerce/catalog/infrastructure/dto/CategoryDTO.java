package com.ecommerce.catalog.infrastructure.dto;


import java.util.Set;
import java.util.UUID;

public record CategoryDTO(
        UUID id,
        String name,
        String description,
        Set<UUID> subCategoryIds,
        Set<UUID> productIds
) {}
