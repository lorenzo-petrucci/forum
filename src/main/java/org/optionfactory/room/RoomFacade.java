package org.optionfactory.room;

import java.util.List;

public interface RoomFacade {
    List<Room> listPublic(int recordPerPage, int pageNumber);
    List<Room> listPrivate(int recordPerPage, int pageNumber);
    List<Room> listSubscribed(int recordPerPage, int pageNumber, long authorId);
    List<Room> listOwned(int recordPerPage, int pageNumber, long authorId);
}
