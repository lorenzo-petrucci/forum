package org.optionfactory.author;

import org.springframework.jdbc.core.JdbcOperations;
import java.util.Optional;

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
    public Optional<Author> readAuthor(String name) {
        return jdbc.query("""
                SELECT * FROM author
                WHERE username = ?
                """,
                (rs, i) -> Author.withId(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getString("privilege"),
                        rs.getBoolean("is_blocked")
                ), name).stream().findFirst();
    }

    @Override
    public Optional<Author> searchById(long id) {
        return jdbc.query("""
                SELECT * FROM author
                WHERE id = ?
                """,
                (rs, i) -> Author.withId(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getString("privilege"),
                        rs.getBoolean("is_blocked")
                ), id).stream().findFirst();
    }


}
