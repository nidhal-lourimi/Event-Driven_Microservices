package com.nidhallourimi.query;

import com.nidhallourimi.core.data.ProductsRepository;
import com.nidhallourimi.core.events.ProductCreatedEvent;
import com.nidhallourimi.core.data.ProductEntity;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private final ProductsRepository productsRepository;

    public ProductEventsHandler(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductEntity productEntity =new ProductEntity();
        BeanUtils.copyProperties(event,productEntity);
        productsRepository.save(productEntity);

    }

}
