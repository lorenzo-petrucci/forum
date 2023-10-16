package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class TransactionalAuthorFacade implements AuthorFacade {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    public TransactionalAuthorFacade(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Long create(AuthorRequest authorRequest) {

//        final String salt = UUID.randomUUID().toString();

        return authorRepository.createAuthor(Author.withoutId(
                authorRequest.username(),
                passwordEncoder.encode(authorRequest.password()),
                "salt",
                Privileges.USER.name(),
                false
        ));
    }

    @Override
    public Optional<Author> read(AuthorRequest authorRequest) {
        return authorRepository.readAuthor(authorRequest.username());
    }

    @Override
    public Author searchById(long id) {
        return authorRepository.searchById(id).orElseThrow();
    }
}
