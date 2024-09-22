package com.example.microservices.order_service.dto;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) { }
