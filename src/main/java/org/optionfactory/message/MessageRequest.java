package org.optionfactory.message;

import java.util.UUID;

public record MessageRequest(UUID uuid, Long authorId, Long threadId, UUID parentUUID, String body, byte[] image) {
}
