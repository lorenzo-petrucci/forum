package org.optionfactory.message;

import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcMessageRepository implements MessageRepository{
    private final JdbcOperations jdbc;

    public JdbcMessageRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Message> listByThreadId(Long threadId) {
        return jdbc.query("""
                        SELECT * FROM message
                        WHERE thread_id = ?
                        """,
                (rs, rowNum) -> new Message(
                        UUID.fromString(rs.getString("uuid")),
                        rs.getLong("authorId"),
                        rs.getLong("thread_id"),
                        rs.getTimestamp("created_at").toInstant(),
                        Optional.ofNullable(rs.getString("parent_uuid")).map(UUID::fromString).orElse(null),
                        rs.getString("body"),
                        rs.getBytes("image"),
                        rs.getBoolean("is_active")
                ), threadId);
    }

    @Override
    public int deleteByUUID(UUID uuid) {
        return jdbc.update("""
                DELETE FROM message
                WHERE uuid = ?
                """,
                uuid
        );
    }

    @Override
    public Optional<Message> searchByUUID(UUID uuid) {
        return jdbc.query("""
                SELECT * FROM message
                WHERE uuid = ?
                """,
                (rs, rowNum) -> new Message(
                        UUID.fromString(rs.getString("uuid")),
                        rs.getLong("authorId"),
                        rs.getLong("thread_id"),
                        rs.getTimestamp("created_at").toInstant(),
                        Optional.ofNullable(rs.getString("parent_uuid")).map(UUID::fromString).orElse(null),
                        rs.getString("body"),
                        rs.getBytes("image"),
                        rs.getBoolean("is_active")
                ), uuid).stream().findFirst();
    }

    @Override
    public void upsert(Message message) {
        jdbc.update("""
                INSERT INTO message (uuid, authorId, thread_id, created_at, parent_uuid, body, image, is_active)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                ON CONFLICT (uuid)
                DO UPDATE
                SET body = ?, image = ?
                """,
                message.getUuid(),
                message.getAuthorId(),
                message.getThreadId(),
                Timestamp.from(message.getCreatedAt()),
                message.getParentUUID().orElse(null),
                message.getBody(),
                message.getImage(),
                message.isActive(),
                message.getBody(),
                message.getImage()
        );
    }
}
