package com.nidhallourimi.query.rest;

import com.nidhallourimi.query.FindProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    QueryGateway queryGateway;

    @Autowired
    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModule> getProducts(){
        FindProductsQuery findProductsQuery=new FindProductsQuery();
     List<ProductRestModule> products=queryGateway.query(findProductsQuery,
             ResponseTypes.multipleInstancesOf(ProductRestModule.class)).join();
     return  products;

    }
}
