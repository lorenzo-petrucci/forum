package org.optionfactory.room;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public record Room(
        long id,
        UUID uuid,
        String title,
        long authorId,
        String authorName,
        Instant createdAt,
        boolean isPublic,
        boolean isActive,
        Optional<Instant> exitedAt
) {

}
