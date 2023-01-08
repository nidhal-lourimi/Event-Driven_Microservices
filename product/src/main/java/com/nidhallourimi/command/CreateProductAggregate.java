package com.nidhallourimi.command;

import com.nidhallourimi.core.commands.ReserveProductCommand;
import com.nidhallourimi.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.math.BigDecimal;

@Aggregate
/*@Profile("command")*/
public class CreateProductAggregate {
    @AggregateIdentifier
    private  String productId;
    private  String title;
    private  BigDecimal price;
    private  Integer quantity;
    public CreateProductAggregate() {
    }
    @CommandHandler
    public CreateProductAggregate(CreateProductCommand productCommand) throws Exception {
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
        // this events will b scheduled for publication to others events and persist in the event store
        //throwing an exception handler will stop command handler from dispatching the data to the event store even
        //our exception is after
    /*    if (true) throw new  Exception("An error took place in CreateProductCommand @CommandHandler exception");*/
    }
    @CommandHandler
    public void handle(ReserveProductCommand reserveProductCommand){
        if (quantity< reserveProductCommand.getQuantity()){
            throw new IllegalArgumentException("Insufficient number of items in stock");
        }

    }
    @EventSourcingHandler
    //initialize the current state of the aggregate state with the latest info
    public void on (ProductCreatedEvent productCreatedEvent){
        this.productId=productCreatedEvent.getProductId();
        this.title=productCreatedEvent.getTitle();
        this.price=productCreatedEvent.getPrice();
        this.quantity=productCreatedEvent.getQuantity();

    }

}
