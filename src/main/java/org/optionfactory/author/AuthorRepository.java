package org.optionfactory.author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Long create(Author author);
    Optional<Author> searchByName(String name);
    Optional<Author> searchById(long id);
    List<Author> listByName(String name, int offset, int limit);
    void updateById(long id, String privilegeType, boolean block);
    Optional<Integer> count();
}
