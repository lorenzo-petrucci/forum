package org.optionfactory.room;

import java.util.List;

public interface RoomRepository {
    List<Room> listPublic(int limit, int offset);
}
