package org.optionfactory.messages;

import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
import java.util.List;

public class JdbcMessageRepository implements MessageRepository{
    private final JdbcOperations jdbc;

    public JdbcMessageRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Message> list(Long threadId) {
        return jdbc.query("""
                        SELECT * FROM message
                        WHERE thread_id = ?
                        """,
                (rs, rowNum) -> Message.withId(
                        rs.getLong("id"),
                        rs.getLong("author_id"),
                        rs.getLong("thread_id"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getLong("parent_id"),
                        rs.getString("body"),
                        rs.getBytes("image"),
                        rs.getBoolean("is_active")
                ), threadId);
    }

    @Override
    public Long create(Message message) {
        return jdbc.queryForObject("""
                INSERT INTO message (author_id, thread_id, created_at, parent_id, body, image, is_active)
                VALUES(?, ?, ?, ?, ?, ?, ?)
                RETURNING id
                """,
                Long.class,
                message.getAuthorId(),
                message.getThreadId(),
                Timestamp.from(message.getCreatedAt()),
                message.getParentId(),
                message.getBody(),
                message.getImage(),
                message.isActive());
    }

    @Override
    public void update(Message message) {
        jdbc.update("""
                UPDATE message
                SET body = ?
                WHERE id = ?
                """,
                message.getBody(),
                message.getId()
        );
    }

    @Override
    public void delete(Message message) {
        jdbc.update("""
                DELETE FROM message
                WHERE id = ?
                """,
                message.getId()
        );
    }
}
