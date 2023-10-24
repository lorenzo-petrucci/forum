package org.optionfactory.room;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public record Room(
        // TODO: 10/24/23 don't need id
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
