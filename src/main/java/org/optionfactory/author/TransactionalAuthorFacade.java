package org.optionfactory.author;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Transactional
public class TransactionalAuthorFacade implements AuthorFacade {
    @Autowired
    private ServletContext servletContext;
    private final AuthorRepository authorRepository;

    public TransactionalAuthorFacade(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Long create(AuthorRequest authorRequest) {

        String salt = "salt"; // FIXME: 10/6/23 implement correct authentication
        String privilege = "user"; // FIXME: 10/6/23 use enum?

        return authorRepository.createAuthor(Author.withoutId(
                authorRequest.name(),
                authorRequest.password(),
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
