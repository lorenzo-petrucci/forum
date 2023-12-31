package org.optionfactory.room;

import org.optionfactory.author.AuthorDetails;

import java.util.List;

public interface RoomFacade {
    List<Room> list(int recordPerPage, int pageNumber, long authorId, RoomType roomType);
    List<Room> listPublic(int recordPerPage, int pageNumber);
    void upsert(RoomRequest roomRequest, AuthorDetails authorDetails);

}
