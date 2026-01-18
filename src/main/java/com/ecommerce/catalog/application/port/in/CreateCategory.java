package com.ecommerce.catalog.application.port.in;


import java.util.UUID;

public interface CreateCategory {
    UUID execute(String name, String description);
}

