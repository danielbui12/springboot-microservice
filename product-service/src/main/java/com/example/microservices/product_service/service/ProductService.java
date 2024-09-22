package com.example.microservices.product_service.service;

import com.example.microservices.product_service.dto.CreateProductDto;
import com.example.microservices.product_service.dto.ProductResponseDto;
import com.example.microservices.product_service.model.Product;
import com.example.microservices.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto createProduct(CreateProductDto productData) {
        Product product = Product.builder()
                .name(productData.name())
                .price(productData.price())
                .description(productData.description())
                .build();
        productRepository.save(product);
        log.info("Product created: {}", product);
        return new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product ->  new ProductResponseDto(product.getId(), product.getName(), product.getDescription(), product.getPrice()))
                .toList();
    }
}
