package com.nidhallourimi.command;

import com.nidhallourimi.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class CreateProductAggregate {
    public CreateProductAggregate() {
    }
    @CommandHandler
    public CreateProductAggregate(CreateProductCommand productCommand) {
        //validate create Product Command
        if(productCommand.getPrice().compareTo(BigDecimal.valueOf(0L))<=0L){
            throw new IllegalArgumentException("price cannot be less or equal to zero");
        }
        if(productCommand.getTitle()==null || productCommand.getTitle().isBlank()){
            throw  new IllegalArgumentException("title cannot be empty");
        }
        // publish event after validation
        ProductCreatedEvent productCreatedEvent=new ProductCreatedEvent();
        BeanUtils.copyProperties(productCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent);
        //when we call apply method on aggregate it will dispatch event to all events handlers the  aggregate state will be updated by new info
        // this events will b scheduled for publication to others events
    }
}
