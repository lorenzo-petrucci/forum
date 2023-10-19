package org.optionfactory.room;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class JdbcRoomRepository implements RoomRepository{
    private final JdbcOperations jdbc;

    public JdbcRoomRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Room> listPublic(int limit, int offset) {
        return jdbc.query("""
                        SELECT * FROM room
                        JOIN author ON author_id = author.id
                        WHERE is_public = true
                        ORDER BY room.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("exited_at") == null ? Optional.empty() : Optional.of(rs.getTimestamp("exited_at").toInstant())
                ), limit, offset);
    }

    @Override
    public List<Room> listPrivate(int limit, int offset) {
        return jdbc.query("""
                        SELECT * FROM room
                        JOIN author ON author_id = author.id
                        WHERE is_public = false
                        ORDER BY room.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("exited_at") == null ? Optional.empty() : Optional.of(rs.getTimestamp("exited_at").toInstant())
                ), limit, offset);
    }

    @Override
    public List<Room> listSubscribed(int limit, int offset, long authorId) {
        return jdbc.query("""
                        SELECT * FROM room
                        JOIN author ON author_id = author.id
                        JOIN subscription ON room.id = subscription.room_id
                        WHERE subscription.author_id = ?
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("exited_at") == null ? Optional.empty() : Optional.of(rs.getTimestamp("exited_at").toInstant())
                ), authorId, limit, offset);
    }

    @Override
    public List<Room> listOwned(int limit, int offset, long authorId) {
        return jdbc.query("""
                        SELECT * FROM room
                        JOIN author ON author_id = author.id
                        WHERE author_id = ?
                        ORDER BY room.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("exited_at") == null ? Optional.empty() : Optional.of(rs.getTimestamp("exited_at").toInstant())
                ), authorId, limit, offset);
    }
}