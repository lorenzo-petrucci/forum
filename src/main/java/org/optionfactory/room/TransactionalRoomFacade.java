package org.optionfactory.room;

import org.optionfactory.author.AuthorDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void upsert(RoomRequest roomRequest, AuthorDetails authorDetails) {
        roomRepository.upsert(new RoomCreation(
                roomRequest.uuid(),
                roomRequest.title(),
                authorDetails.getId(),
                authorDetails.getUsername(),
                roomRequest.createdAt(),
                roomRequest.isPublic(),
                roomRequest.isActive(),
                Optional.empty()
        ));
    }
}
