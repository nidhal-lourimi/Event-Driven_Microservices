package com.nidhallourimi.contoller;

import com.nidhallourimi.command.CreateProductCommand;
import com.nidhallourimi.model.CreateProductRestModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;


@RestController
@RequestMapping("products")

public class ProductsController {

    Environment environment;
    CommandGateway commandGateway;

    public ProductsController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel productRestModel){
        /*CompletableFuture<String> */
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(productRestModel.getTitle())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();
        String returnValue = " ";
        try {
            returnValue = commandGateway.sendAndWait(createProductCommand);
        }catch (Exception e){
            returnValue =e.getLocalizedMessage();
        }
        return returnValue;
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GEt Handled with a port number ="+environment.getProperty("local.server.port");

    }

    @PutMapping
    public String putProduct(){
        return "HTTP PUT Handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "HTTP DELETE Handled";
    }


}
