package org.optionfactory.author;

import java.util.Optional;

public interface AuthorRepository {
    Long create(Author author);
    Optional<Author> searchByName(String name);
    Optional<Author> searchById(long id);
}
