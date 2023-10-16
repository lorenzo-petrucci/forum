package org.optionfactory.author;

import java.util.Optional;

public interface AuthorRepository {
    Long createAuthor(Author author);
    Optional<Author> readAuthor(String name);
    Optional<Author> searchById(long id);
}
