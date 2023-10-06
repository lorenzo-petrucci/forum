package org.optionfactory.messages;

import java.time.Instant;

public record MessageRequest(Long authorId, Long threadId, Long parentId, String body, byte[] image) {
}
