package org.optionfactory.message;

import java.util.List;
import java.util.UUID;

public interface MessageFacade {
    List<Message> list(Long threadId);
    void delete(UUID uuid);
    Message search(UUID uuid);
    void upsert(MessageRequest messageRequest);
}
