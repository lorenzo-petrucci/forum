package org.optionfactory.messages;

import java.util.List;

public interface MessageRepository {
    List<Message> list();
    Long create(Message message);
    void update(Message message);
    Long delete(Message message);

}
