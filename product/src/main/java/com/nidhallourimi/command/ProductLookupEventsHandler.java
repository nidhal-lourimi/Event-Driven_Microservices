package com.nidhallourimi.command;

import com.nidhallourimi.command.data.ProductLookupEntity;
import com.nidhallourimi.command.data.ProductLookupRepository;
import com.nidhallourimi.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
//event handled only once and handled in the same thread(give us the possibilities to roll back all transaction if event processing not successful)
//optional if all the event handler are in the same package
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
   private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on (ProductCreatedEvent event){
        ProductLookupEntity productLookupEntity=new ProductLookupEntity(event.getProductId(),event.getTitle());
        //you can add a check here(if product record exit or not)
        productLookupRepository.save(productLookupEntity);

    }

}
