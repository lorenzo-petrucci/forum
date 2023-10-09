package org.optionfactory.message;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class Message {
    private final UUID uuid;
    private final Long authorId;
    private final Long threadId;
    private final Instant createdAt;
    private final Optional<UUID> parentUUID;
    private final String body;
    private final byte[] image;
    private final Boolean isActive;

    public Message(UUID uuid, Long authorId, Long threadId, Instant createdAt, UUID parentUUID, String body, byte[] image, Boolean isActive) {
        this.uuid = uuid;
        this.authorId = authorId;
        this.threadId = threadId;
        this.createdAt = createdAt;
        this.parentUUID = Optional.ofNullable(parentUUID);
        this.body = body;
        this.image = image;
        this.isActive = isActive;
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

    public Optional<UUID> getParentUUID() {
        return parentUUID;
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

    public UUID getUuid() {
        return uuid;
    }
}
