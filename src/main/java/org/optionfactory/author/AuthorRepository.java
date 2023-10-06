package org.optionfactory.author;

public interface AuthorRepository {
    Long createAuthor(Author author);
    Author readAuthor(String name, String password);
}
