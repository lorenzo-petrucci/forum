package org.optionfactory.setup;

import org.optionfactory.author.AuthorFacade;
import org.optionfactory.author.AuthorRepository;
import org.optionfactory.author.TransactionalAuthorFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorConfig {
    @Bean
    public AuthorFacade authorFacade(AuthorRepository authorRepository) {
        return new TransactionalAuthorFacade(authorRepository);
    }
}
