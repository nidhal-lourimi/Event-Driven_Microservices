package com.nidhallourimi;

import com.nidhallourimi.command.interceptors.CreateProductCommandInterceptor;
import com.nidhallourimi.core.errorhandling.ProductsServiceEventErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableDiscoveryClient //optional

public class ProductApplication {
    public static void main(String[]args){
        SpringApplication.run(ProductApplication.class,args);
    }
    @Autowired
    public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus){
    commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
    }
    @Autowired
    public void configure(EventProcessingConfigurer config){
        config.registerListenerInvocationErrorHandler("product-group",
                configuration -> new ProductsServiceEventErrorHandler());

    }
}


