package org.optionfactory.room;

import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

public class JdbcRoomRepository implements RoomRepository{
    private final JdbcOperations jdbc;

    public JdbcRoomRepository(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Room> listPublicRooms() {
        return jdbc.query("""
                        SELECT * FROM room
                        WHERE is_public = true
                        ORDER BY created_at DESC
                        LIMIT 10
                        """,
                (rs, rowNum) -> new Room(
                        rs.getLong("id"),
                        UUID.fromString(rs.getString("uuid")),
                        rs.getString("title"),
                        rs.getLong("author_id"),
                        rs.getTimestamp("created_at").toInstant(),
                        rs.getBoolean("is_public"),
                        rs.getBoolean("is_active"),
                        rs.getTimestamp("created_at").toInstant()
                ));
    }
}