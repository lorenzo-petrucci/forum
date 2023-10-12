package org.optionfactory.author;

import java.util.Optional;

public interface AuthorFacade {
    Long create(AuthorRequest authorRequest);
    Optional<Author> read(AuthorRequest authorRequest);
}
