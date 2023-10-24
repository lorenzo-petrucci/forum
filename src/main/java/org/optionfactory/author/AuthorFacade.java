package org.optionfactory.author;

import java.util.List;

public interface AuthorFacade {
    long create(AuthorRequest authorRequest);
    Author searchByName(String name);
    Author searchById(long id);
    List<Author> listByName(String name);
    void updateBlockStatusById(long id, boolean block);
}
