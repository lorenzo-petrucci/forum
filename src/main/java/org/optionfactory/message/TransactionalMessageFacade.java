package org.optionfactory.message;

import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;

@Transactional
public class TransactionalMessageFacade implements MessageFacade{
    private final MessageRepository messageRepository;
    private final Clock clock;

    public TransactionalMessageFacade(MessageRepository messageRepository, Clock clock) {
        this.messageRepository = messageRepository;
        this.clock = clock;
    }

    @Override
    public List<Message> list(Long threadId) {
        return messageRepository.list(threadId);
    }

    @Override
    public Long create(MessageRequest messageRequest) {
        return messageRepository.create(Message.withoutId(
                messageRequest.authorId(),
                messageRequest.threadId(),
                clock.instant(),
                messageRequest.parentId(),
                messageRequest.body(),
                messageRequest.image(),
                true
        ));
    }

    @Override
    public void update(Long id, MessageRequest messageRequest) {
        messageRepository.update(Message.toUpdate(id, messageRequest.body()));
    }

    @Override
    public void delete(Long id, MessageRequest messageRequest) {
        messageRepository.delete(Message.toUpdate(id, messageRequest.body()));
    }


}
