package org.optionfactory.setup;

import org.optionfactory.message.MessageFacade;
import org.optionfactory.message.MessageRepository;
import org.optionfactory.message.TransactionalMessageFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class MessageConfig {
    @Bean
    public MessageFacade messageFacade(MessageRepository messageRepository, Clock clock) {
        return new TransactionalMessageFacade(messageRepository, clock);
    }
}
