package com.example.microservices.order_service.service;

import com.example.microservices.order_service.client.InventoryClient;
import com.example.microservices.order_service.dto.OrderRequest;
import com.example.microservices.order_service.dto.OrderResponse;
import com.example.microservices.order_service.event.OrderPlacedEvent;
import com.example.microservices.order_service.model.Order;
import com.example.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public Order placeAnOrder(OrderRequest orderData) {
//        boolean isExistingItem = inventoryClient.isInStock(orderData.skuCode(), orderData.quantity());
//        if (isExistingItem) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderData.price());
            order.setQuantity(orderData.quantity());
            order.setSkuCode(orderData.skuCode());
            orderRepository.save(order);

            // send message to Kafka
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderData.userDetails().email());
            orderPlacedEvent.setFirstName(orderData.userDetails().firstName());
            orderPlacedEvent.setLastName(orderData.userDetails().lastName());
            String ORDER_PLACED_EVENT = "order-placed";
            log.info("Start sending {} topic to Kafka", ORDER_PLACED_EVENT);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End sending {} topic to Kafka", ORDER_PLACED_EVENT);
            return order;
//        } else {
//            throw new RuntimeException("Item is not existed in Inventory");
//        }
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
