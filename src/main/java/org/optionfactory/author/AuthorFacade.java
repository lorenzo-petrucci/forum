package org.optionfactory.author;

public interface AuthorFacade {
    long create(AuthorRequest authorRequest);
    Author searchByName(String name);
    Author searchById(long id);
}
