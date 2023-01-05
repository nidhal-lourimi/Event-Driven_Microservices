package com.nidhallourimi.command;

import com.nidhallourimi.command.OrderStatus;
import com.nidhallourimi.core.events.OrderCreatedEvent;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Aggregate
@NoArgsConstructor

public class OrderAggregate {
    @AggregateIdentifier
    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;
    @CommandHandler
    public OrderAggregate(CreateOrderCommand orderCommand){


        OrderCreatedEvent orderCreatedEvent=new OrderCreatedEvent();
        BeanUtils.copyProperties(orderCommand,orderCreatedEvent);
        AggregateLifecycle.apply(orderCreatedEvent);

    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        this.orderId=orderCreatedEvent.getOrderId();
        this.productId=orderCreatedEvent.getProductId();
        this.userId= orderCreatedEvent.getUserId();
        this.quantity=orderCreatedEvent.getQuantity();
        this.addressId= orderCreatedEvent.getAddressId();
        this.orderStatus=orderCreatedEvent.getOrderStatus();

    }

}
