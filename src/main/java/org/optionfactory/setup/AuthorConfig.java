package org.optionfactory.setup;

import org.optionfactory.author.AuthorFacade;
import org.optionfactory.author.AuthorRepository;
import org.optionfactory.author.TransactionalAuthorFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthorConfig {
    @Bean
    public AuthorFacade authorFacade(PasswordEncoder passwordEncoder, AuthorRepository authorRepository) {
        return new TransactionalAuthorFacade(passwordEncoder, authorRepository);
    }
}
