package org.optionfactory.room;

import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Timestamp;
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
                        SELECT r.*, a.name as author_name FROM room r
                        JOIN author a ON author_id = a.id
                        WHERE is_public = true
                        ORDER BY r.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        Optional.ofNullable(rs.getTimestamp("exited_at")).map(Timestamp::toInstant)
                ), limit, offset);
    }

    @Override
    public List<Room> listPrivate(int limit, int offset) {
        return jdbc.query("""
                        SELECT r.*, a.name as author_name FROM room r
                        JOIN author a ON author_id = a.id
                        WHERE is_public = false
                        ORDER BY r.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        Optional.ofNullable(rs.getTimestamp("exited_at")).map(Timestamp::toInstant)
                ), limit, offset);
    }

    @Override
    public List<Room> listSubscribed(int limit, int offset, long authorId) {
        return jdbc.query("""
                        SELECT r.*, a.name as author_name FROM room r
                        JOIN author a ON author_id = a.id
                        JOIN subscription s ON r.id = s.room_id
                        WHERE s.author_id = ?
                        ORDER BY r.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        Optional.ofNullable(rs.getTimestamp("exited_at")).map(Timestamp::toInstant)
                ), authorId, limit, offset);
    }

    @Override
    public List<Room> listOwned(int limit, int offset, long authorId) {
        return jdbc.query("""
                        SELECT r.*, a.name as author_name FROM room r
                        JOIN author a ON author_id = a.id
                        WHERE author_id = ?
                        ORDER BY r.created_at DESC
                        LIMIT ? OFFSET ?
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getString("author_name"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        Optional.ofNullable(rs.getTimestamp("exited_at")).map(Timestamp::toInstant)
                ), authorId, limit, offset);
    }

    @Override
    public void upsert(RoomRequest roomRequest) {
        jdbc.update("""
                INSERT INTO room (uuid, title, author_id, created_at, is_public, is_active)
                VALUES(?, ?, ?, ?, ?, ?)
                ON CONFLICT (uuid)
                DO UPDATE
                SET title = ?, is_public = ?
                """,
                roomRequest.uuid(),
                roomRequest.title(),
                roomRequest.authorId(),
                Timestamp.from(roomRequest.createdAt()),
                roomRequest.isPublic(),
                roomRequest.isActive(),
                roomRequest.title(),
                roomRequest.isPublic()
        );
    }
}