package com.nidhallourimi.command.rest;

import com.nidhallourimi.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("products")

public class ProductsCommandController {

    Environment environment;
    CommandGateway commandGateway;

    public ProductsCommandController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct (@Valid @RequestBody  CreateProductRestModel productRestModel){
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

/*    @GetMapping
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
    }*/


}
