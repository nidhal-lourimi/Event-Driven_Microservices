package com.nidhallourimi.command;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.spring.stereotype.Aggregate;

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

    }
}
