package org.optionfactory.message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository {
    List<Message> listByThread(Long threadId);
    int delete(UUID uuid);
    Optional<Message> searchById(UUID uuid);
    void upsert(Message message);

}
