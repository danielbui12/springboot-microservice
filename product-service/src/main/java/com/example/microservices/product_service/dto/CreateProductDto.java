package com.example.microservices.product_service.dto;

import java.math.BigDecimal;

public record CreateProductDto (
    String name,
    String description,
    BigDecimal price
) { }
