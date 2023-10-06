package org.optionfactory.message;

import java.util.List;

public interface MessageFacade {
    List<Message> list(Long threadId);
    Long create(MessageRequest messageRequest);
    void update(Long id, MessageRequest messageRequest);
    void delete(Long id, MessageRequest messageRequest);
}
