package org.optionfactory.author;

import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
import java.util.Optional;

public class JdbcAuthorRepository implements AuthorRepository{
    private final JdbcOperations jdbc;

    public JdbcAuthorRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Long create(Author author) {
        return jdbc.queryForObject("""
                INSERT INTO author (name, password, salt, privilege, is_blocked, created_at)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING id
                """,
                Long.class,
                author.getName(),
                author.getPassword(),
                author.getSalt(),
                author.getPrivilege(),
                author.isBlocked(),
                Timestamp.from(author.getCreatedAt())
        );
    }

    @Override
    public Optional<Author> searchByName(String name) {
        return jdbc.query("""
                SELECT * FROM author
                WHERE name = ?
                """,
                (rs, i) -> Author.withId(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("salt"),
                        rs.getString("privilege"),
                        rs.getBoolean("is_blocked"),
                        rs.getTimestamp("created_at").toInstant()
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
                        rs.getBoolean("is_blocked"),
                        rs.getTimestamp("created_at").toInstant()
                ), id).stream().findFirst();
    }
}
