package org.optionfactory.room;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TransactionalRoomFacade implements RoomFacade {
    private final RoomRepository roomRepository;

    public TransactionalRoomFacade(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> listPublic(int recordPerPage, int pageNumber) {
        final int offset = pageNumber * recordPerPage;
        return roomRepository.listPublic(recordPerPage, offset);
    }

    @Override
    public List<Room> listPrivate(int recordPerPage, int pageNumber) {
        final int offset = pageNumber * recordPerPage;
        return roomRepository.listPrivate(recordPerPage, offset);
    }

    @Override
    public List<Room> listSubscribed(int recordPerPage, int pageNumber, long authorId) {
        final int offset = pageNumber * recordPerPage;
        return roomRepository.listSubscribed(recordPerPage, offset, authorId);
    }

    @Override
    public List<Room> listOwned(int recordPerPage, int pageNumber, long authorId) {
        final int offset = pageNumber * recordPerPage;
        return roomRepository.listOwned(recordPerPage, offset, authorId);
    }
}
