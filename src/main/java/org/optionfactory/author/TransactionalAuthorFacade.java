package org.optionfactory.author;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Transactional
public class TransactionalAuthorFacade implements AuthorFacade {
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    public TransactionalAuthorFacade(PasswordEncoder passwordEncoder, AuthorRepository authorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
    }

    @Override
    public long create(AuthorRequest authorRequest) {
        return authorRepository.createAuthor(Author.withoutId(
                authorRequest.username(),
                passwordEncoder.encode(authorRequest.password()),
                "salt",
                Privileges.USER.name(),
                false,
                Instant.now()
        ));
    }

    @Override
    public Author searchByName(String name) {
        return authorRepository.searchByName(name).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public Author searchById(long id) {
        return authorRepository.searchById(id).orElseThrow(AuthorNotFoundException::new);
    }
}
