package org.optionfactory.author;

public class Author {
    //(Long id, String name, String password, String salt, String privilege, Boolean is_blocked)
    private final Long id;
    private final String name;
    private final String password;
    private final String salt;
    private final String privilege;
    private final Boolean isBlocked;

    private Author(Long id, String name, String password, String salt, String privilege, Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.privilege = privilege;
        this.isBlocked = isBlocked;
    }

    public static Author withoutId(String name, String password, String salt, String privilege, Boolean isBlocked) {
        return new Author(null, name, password, salt, privilege, isBlocked);
    }

    public static Author withId(Long id, String name, String password, String salt, String privilege, Boolean isBlocked) {
        return new Author(id, name, password, salt, privilege, isBlocked);
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
}
