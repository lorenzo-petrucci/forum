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

        String salt = "salt"; // FIXME: 10/6/23 implement correct authentication
        String privilege = Privileges.user();

        return authorRepository.createAuthor(Author.withoutId(
                authorRequest.name(),
                passwordEncoder.encode(authorRequest.password()),
                salt,
                privilege,
                false
        ));
    }

    @Override
    public Optional<Author> read(AuthorRequest authorRequest) {
        return authorRepository.readAuthor(authorRequest.name());
    }
}
