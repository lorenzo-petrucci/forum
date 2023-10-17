package org.optionfactory.author;

import java.time.Instant;

public class Author {
    private final Long id;
    private final String name;
    private final String password;
    private final String salt;
    private final String privilege;
    private final Boolean isBlocked;
    private final Instant createdAt;

    private Author(Long id, String name, String password, String salt, String privilege, Boolean isBlocked, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.privilege = privilege;
        this.isBlocked = isBlocked;
        this.createdAt = createdAt;
    }

    public static Author withoutId(String name, String password, String salt, String privilege, Boolean isBlocked, Instant createdAt) {
        return new Author(null, name, password, salt, privilege, isBlocked, createdAt);
    }

    public static Author withId(Long id, String name, String password, String salt, String privilege, Boolean isBlocked, Instant createdAt) {
        return new Author(id, name, password, salt, privilege, isBlocked, createdAt);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getPrivilege() {
        return privilege;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
