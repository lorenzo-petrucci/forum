package org.optionfactory.room;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TransactionalRoomFacade implements RoomFacade {
    private final RoomRepository roomRepository;

    public TransactionalRoomFacade(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> listPublic() {
        return roomRepository.listPublic();
    }
}
