package com.nidhallourimi.query;

import com.nidhallourimi.core.data.OrderEntity;
import com.nidhallourimi.core.data.OrderRepository;
import com.nidhallourimi.core.events.OrderCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderEventsHandler {
    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent orderCreatedEvent){
        OrderEntity orderEntity=new OrderEntity();
        BeanUtils.copyProperties(orderCreatedEvent,orderEntity);
        orderRepository.save(orderEntity);

    }
}
