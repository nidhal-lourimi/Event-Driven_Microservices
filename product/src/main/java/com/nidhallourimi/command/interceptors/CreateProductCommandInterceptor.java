package com.nidhallourimi.command.interceptors;

import com.nidhallourimi.command.CreateProductCommand;
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
    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index,command)->{
            log.info("intercepted command = "+command.getPayload());
            if (CreateProductCommand.class.equals(command.getPayload())){
                CreateProductCommand castCommand = (CreateProductCommand) command.getPayload();
                //validate create Product Command
                if(castCommand.getPrice().compareTo(BigDecimal.valueOf(0L))<=0L){
                    throw new IllegalArgumentException("price cannot be less or equal to zero");
                }
                if(castCommand.getTitle()==null || castCommand.getTitle().isBlank()){
                    throw  new IllegalArgumentException("title cannot be empty");
                }
            }
            return command;
        };
    }
}
