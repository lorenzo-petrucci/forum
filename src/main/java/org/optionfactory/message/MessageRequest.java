package org.optionfactory.message;

public record MessageRequest(Long authorId, Long threadId, Long parentId, String body, byte[] image) {
}
