package com.techie.microservices.orderservice.controller;

import com.techie.microservices.orderservice.client.InventoryClient;
import com.techie.microservices.orderservice.dto.OrderDto;
import com.techie.microservices.orderservice.model.Order;
import com.techie.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto){
        boolean allProductsInStock = orderDto.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> inventoryClient.checkStock(orderLineItems.getSkuCode()));
        if(allProductsInStock){
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());
            orderRepository.save(order);
            return "Order placed successfully!";
        }
        else return "Order failed! One of the product is not available in the stock";
    }

}
