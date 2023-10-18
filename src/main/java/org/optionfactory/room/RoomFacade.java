package org.optionfactory.room;

import java.util.List;

public interface RoomFacade {
    List<Room> listPublic(int recordPerPage, int pageNumber);
}
