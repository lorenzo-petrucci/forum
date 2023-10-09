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
//        final Optional<Message> message = messageRepository.searchById(id);
//        if (message.isEmpty()) {
//            throw new MessageNotFoundException(String.format("message with id <%s> not found", id));
//        }
//
        // TODO: 10/9/23 check if current author has privileges
        messageRepository.delete(uuid);

//        final Long authorId;
//        try{
//            authorId = messageRepository.searchById(id).orElseThrow().getAuthorId();
//        } catch (NoSuchElementException e) {
//            throw new MessageNotFoundException();
//        }
//        messageRepository.delete(id);
    }

    @Override
    public Message search(UUID messageUUID) {
        return messageRepository.searchById(messageUUID).orElseThrow(MessageNotFoundException::new);
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
