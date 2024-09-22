package com.example.microservices.order_service.controller;

import com.example.microservices.order_service.dto.OrderRequest;
import com.example.microservices.order_service.dto.OrderResponse;
import com.example.microservices.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeAnOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeAnOrder(orderRequest);
        return "Placed an order successfully!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }
}
