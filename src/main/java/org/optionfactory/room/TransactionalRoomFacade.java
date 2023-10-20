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
    public List<Room> list(int recordPerPage, int pageNumber, long authorId, String roomType) {
        final int offset = pageNumber * recordPerPage;
        return switch (roomType.toUpperCase()) {

            // FIXME: 10/20/23 ENUM???

            case RoomType.PUBLIC -> roomRepository.listPublic(recordPerPage, offset);
            case RoomType.PRIVATE -> roomRepository.listPrivate(recordPerPage, offset);
            case RoomType.SUBSCRIBED -> roomRepository.listSubscribed(recordPerPage, offset, authorId);
            case RoomType.OWNED -> roomRepository.listOwned(recordPerPage, pageNumber, authorId);
            default -> new ArrayList<>();
        };
    }
}
