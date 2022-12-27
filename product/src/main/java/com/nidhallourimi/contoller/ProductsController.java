package com.nidhallourimi.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductsController {
    @Autowired
    Environment environment;
    @PostMapping
    public String createProduct(){
        return "HTTP POSt Handled";
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
