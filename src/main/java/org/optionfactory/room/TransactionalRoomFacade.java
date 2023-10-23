package org.optionfactory.room;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class TransactionalRoomFacade implements RoomFacade {
    private final RoomRepository roomRepository;

    public TransactionalRoomFacade(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> list(int recordPerPage, int pageNumber, long authorId, RoomType roomType) {
        final int offset = pageNumber * recordPerPage;
        return switch (roomType) {
            case PUBLIC -> roomRepository.listPublic(recordPerPage, offset);
            case PRIVATE -> roomRepository.listPrivate(recordPerPage, offset);
            case SUBSCRIBED -> roomRepository.listSubscribed(recordPerPage, offset, authorId);
            case OWNED -> roomRepository.listOwned(recordPerPage, pageNumber, authorId);
        };
    }

    @Override
    public List<Room> listPublic(int recordPerPage, int pageNumber) {
        final int offset = pageNumber * recordPerPage;
        return roomRepository.listPublic(recordPerPage, offset);
    }
}
