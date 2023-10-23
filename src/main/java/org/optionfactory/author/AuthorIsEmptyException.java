package org.optionfactory.author;

public class AuthorIsEmptyException extends RuntimeException{
    public final String message;

    public AuthorIsEmptyException() {
        this.message = "Username and password should not be empty";
    }
}
