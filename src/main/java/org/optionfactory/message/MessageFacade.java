package org.optionfactory.message;

import java.util.List;
import java.util.UUID;

public interface MessageFacade {
    List<Message> listByThreadId(Long threadId);
    void deleteByUUID(UUID uuid);
    Message searchByUUID(UUID uuid);
    void upsert(MessageRequest messageRequest);
}
