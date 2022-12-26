package com.nidhallourimi.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @PostMapping
    public String createProduct(){
        return "HTTP POSt Handled";
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GEt Handled";

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
