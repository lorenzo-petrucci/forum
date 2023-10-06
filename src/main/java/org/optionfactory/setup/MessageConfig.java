package org.optionfactory.setup;

import org.optionfactory.messages.MessageFacade;
import org.optionfactory.messages.MessageRepository;
import org.optionfactory.messages.TransactionalMessageFacade;
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
