package org.optionfactory.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    List<Message> listByThreadId(Long threadId);
    int deleteByUUID(UUID uuid);
    Optional<Message> searchByUUID(UUID uuid);
    void upsert(Message message);

}
