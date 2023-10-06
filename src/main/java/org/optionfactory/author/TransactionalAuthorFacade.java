package org.optionfactory.author;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TransactionalAuthorFacade implements AuthorFacade {
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
}
