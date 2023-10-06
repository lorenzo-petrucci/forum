package org.optionfactory.messages;

import java.time.Instant;

public class Message {
    private final Long id;
    private final Long authorId;
    private final Long threadId;
    private final Instant createdAt;
    private final Long parentId;
    private final String body;
    private final byte[] image;
    private final boolean isActive;

    private Message(Long id, Long authorId, Long threadId, Instant createdAt, Long parentId, String body, byte[] image, boolean isActive) {
        this.id = id;
        this.authorId = authorId;
        this.threadId = threadId;
        this.createdAt = createdAt;
        this.parentId = parentId;
        this.body = body;
        this.image = image;
        this.isActive = isActive;
    }

    public static Message withoutId(Long authorId, Long threadId, Instant createdAt, Long parentId, String body, byte[] image, boolean isActive) {
        return new Message(null, authorId, threadId, createdAt, parentId, body, image, isActive);
    }

    public static Message withId(Long id, Long authorId, Long threadId, Instant createdAt, Long parentId, String body, byte[] image, boolean isActive) {
        return new Message(id, authorId, threadId, createdAt, parentId, body, image, isActive);
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getThreadId() {
        return threadId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getBody() {
        return body;
    }

    public byte[] getImage() {
        return image;
    }

    public boolean isActive() {
        return isActive;
    }
}
