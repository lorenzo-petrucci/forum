package org.optionfactory.author;

import org.springframework.jdbc.core.JdbcOperations;

public class JdbcAuthorRepository implements AuthorRepository{
    private final JdbcOperations jdbc;

    public JdbcAuthorRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Long createAuthor(Author author) {
        return jdbc.queryForObject("""
                INSERT INTO author (name, password, salt, privilege, is_blocked)
                VALUES (?, ?, ?, ?, ?)
                RETURNING id
                """,
                Long.class,
                author.getName(),
                author.getPassword(),
                author.getSalt(),
                author.getPrivilege(),
                author.isBlocked()
        );
    }

    @Override
    public Author readAuthor(String name, String password) {
        return null;
    }
}
