package com.example.ordersservice;

import org.axonframework.eventsourcing.EventSourcingHandler;

public class OrderAggregate {
    @EventSourcingHandler
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
}
