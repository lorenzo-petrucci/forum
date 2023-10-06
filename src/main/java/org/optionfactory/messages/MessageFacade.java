package org.optionfactory.messages;

import java.util.List;

public interface MessageFacade {
    List<Message> list();
    Long create(MessageRequest messageRequest);
}
