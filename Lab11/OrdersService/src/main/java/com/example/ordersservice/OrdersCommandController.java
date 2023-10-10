package com.example.ordersservice;


import org.springframework.web.bind.annotation.*;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.UUID;

import static com.example.ordersservice.OrderStatus.*;

@RestController
@RequestMapping(value = "/orders", method = RequestMethod.POST)
public class OrdersCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @Autowired
    public OrdersCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateOrderCommand model) {
//        return "HTTP POST handled";
        CreateOrderCommand command = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(model.getProductId())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .orderStatus(model.getOrderStatus())
//                .quantity(model.getQuantity())
//                .addressId(model.getAddressId())
                .build();

        String result;
        try {
            result = commandGateway.sendAndWait(command);
        } catch (Exception e) {
            result = e.getLocalizedMessage();
        }

        return result;

    }

}
