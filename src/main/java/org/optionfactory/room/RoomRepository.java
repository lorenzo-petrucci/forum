package org.optionfactory.room;

import java.util.List;

public interface RoomRepository {
    List<Room> listPublic(int limit, int offset);
    List<Room> listPrivate(int recordPerPage, int pageNumber);
    List<Room> listSubscribed(int recordPerPage, int pageNumber, long authorId);
    List<Room> listOwned(int recordPerPage, int pageNumber, long authorId);
    void upsert(RoomRequest roomRequest);
}
