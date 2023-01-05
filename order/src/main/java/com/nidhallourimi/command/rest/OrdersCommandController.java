package com.nidhallourimi.command.rest;

import com.nidhallourimi.command.CreateOrderCommand;
import com.nidhallourimi.command.OrderStatus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public OrdersCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createOrder(@Valid @RequestBody CreateRestOrderModel createRestOrderModel) {

        CreateOrderCommand orderCommand = CreateOrderCommand.builder()
                .orderId(UUID.randomUUID().toString())
                .productId(createRestOrderModel.getProductId())
                .userId("27b95829-4f3f-4ddf-8983-151ba010e35b")
                .quantity(createRestOrderModel.getQuantity())
                .addressId(createRestOrderModel.getAddressId())
                .orderStatus(OrderStatus.APPROVED).build();
        try {
            return commandGateway.sendAndWait(orderCommand).toString();
        } catch (Exception e) {
            return e.getLocalizedMessage();

        }


    }
}
