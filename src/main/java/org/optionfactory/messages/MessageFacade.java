package org.optionfactory.messages;

import java.util.List;

public interface MessageFacade {
    List<Message> list();
    Long create(MessageRequest messageRequest);
    void update(Long id, MessageRequest messageRequest);
    void delete(Long id, MessageRequest messageRequest);
}
