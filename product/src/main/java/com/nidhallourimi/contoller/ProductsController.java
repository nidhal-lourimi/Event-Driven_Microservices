package com.nidhallourimi.contoller;

import com.nidhallourimi.command.CreateProductCommand;
import com.nidhallourimi.model.CreateProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("products")
public class ProductsController {

    Environment environment;
    CommandGateway commandGateway;
    @Autowired
    public ProductsController(Environment environment, CommandGateway commandGateway) {
        this.environment = environment;
        this.commandGateway = commandGateway;
    }



    @PostMapping
    public String createProduct(@RequestBody CreateProductRestModel productRestModel){
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .title(productRestModel.getTitle())
                .price(productRestModel.getPrice())
                .quantity(productRestModel.getQuantity())
                .build();
        String returnValue;
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
