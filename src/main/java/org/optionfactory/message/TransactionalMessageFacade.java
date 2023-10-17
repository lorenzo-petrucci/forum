package org.optionfactory.message;

import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.util.List;
import java.util.UUID;

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
        return messageRepository.listByThread(threadId);
    }

    @Override
    public void delete(UUID uuid) {
        messageRepository.delete(uuid);
    }

    @Override
    public Message search(UUID uuid) {
        return messageRepository.searchById(uuid).orElseThrow(MessageNotFoundException::new);
    }

    @Override
    public void upsert(MessageRequest messageRequest) {
        messageRepository.upsert(new Message(
                messageRequest.uuid(),
                messageRequest.authorId(),
                messageRequest.threadId(),
                clock.instant(),
                messageRequest.parentUUID(),
                messageRequest.body(),
                messageRequest.image(),
                true)
        );
    }

}
