package com.example.microservices.order_service.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

@Slf4j
public interface InventoryClient {

    Logger logger = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
    @Retry(name = "inventory")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(@RequestParam String skuCode, @RequestParam Integer quantity, Throwable throwable) {
        logger.info("Cannot get inventory for skuCode {}, failure reason: {}", skuCode, throwable.getMessage());
        return false;
    }
}
