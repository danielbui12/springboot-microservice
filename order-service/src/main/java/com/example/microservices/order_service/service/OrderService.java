package com.example.microservices.order_service.service;

import com.example.microservices.order_service.dto.OrderRequest;
import com.example.microservices.order_service.dto.OrderResponse;
import com.example.microservices.order_service.model.Order;
import com.example.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeAnOrder(OrderRequest orderData) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderData.price());
        order.setQuantity(orderData.quantity());
        order.setSkuCode(orderData.skuCode());

        orderRepository.save(order);

        return order;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> new OrderResponse(
                order.getOrderId(),
                order.getOrderNumber(),
                order.getSkuCode(),
                order.getPrice(),
                order.getQuantity()
        )).toList();
    }
}
