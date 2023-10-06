package org.optionfactory.message;

import java.util.List;

public interface MessageRepository {
    List<Message> list(Long threadId);
    Long create(Message message);
    void update(Message message);
    void delete(Message message);

}
