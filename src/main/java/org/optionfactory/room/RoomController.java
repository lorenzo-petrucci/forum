package org.optionfactory.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomFacade roomFacade;

    @GetMapping("/listPublic")
    public List<Room> listPublicRooms() {
        return roomFacade.listPublicRooms();
    }
}
