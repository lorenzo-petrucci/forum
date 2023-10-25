package org.optionfactory.author;

public interface AuthorFacade {
    long create(AuthorRequest authorRequest);
    Author searchByName(String name);
    Author searchById(long id);
    BootstrapTableAuthorList listByName(String name, int offset, int limit);
    void updateById(long id, String privilegeType, boolean block);
}
