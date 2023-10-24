package org.optionfactory.room;

import java.time.Instant;
import java.util.UUID;

public record RoomRequest (
        UUID uuid,
        String title,
        long authorId,
        Instant createdAt,
        boolean isPublic,
        boolean isActive
) {

}