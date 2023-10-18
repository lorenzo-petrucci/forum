package org.optionfactory.author;

public class AuthorNotFoundException extends RuntimeException{
    public final String message;

    public AuthorNotFoundException(String name) {
        this.message = "Author name " + name + " not found";
    }

    public AuthorNotFoundException(long id) {
        this.message = "Author id " + id + " not found";
    }
}
