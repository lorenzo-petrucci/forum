package org.optionfactory.author;

public class AuthorAlreadyExistsException  extends RuntimeException {
    public final String message;

    public AuthorAlreadyExistsException(String name) {
        this.message = "Author name " + name + " already exists";
    }
}
