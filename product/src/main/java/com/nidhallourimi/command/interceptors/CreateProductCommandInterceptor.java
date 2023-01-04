package com.nidhallourimi.command.interceptors;

import com.nidhallourimi.command.CreateProductCommand;
import com.nidhallourimi.command.data.ProductLookupEntity;
import com.nidhallourimi.command.data.ProductLookupRepository;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;
@Log4j2
@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index,command)->{
            log.info("intercepted command = "+command.getPayload());
            //check if you intercepted the right command
            if (CreateProductCommand.class.equals(command.getPayload())){
                CreateProductCommand castCommand = (CreateProductCommand) command.getPayload();
                //validate create Product Command
                if(castCommand.getPrice().compareTo(BigDecimal.valueOf(0L))<=0L){
                    throw new IllegalArgumentException("price cannot be less or equal to zero");
                }
                if(castCommand.getTitle()==null || castCommand.getTitle().isBlank()){
                    throw  new IllegalArgumentException("title cannot be empty");
                }
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(castCommand.getProductId(), castCommand.getTitle());
                if(productLookupEntity!=null){
                    throw new IllegalStateException(String.format("product with productId %s or title %s already exist ",castCommand.getTitle(),castCommand.getTitle()));
                }


            }
            return command;
        };
    }
}
