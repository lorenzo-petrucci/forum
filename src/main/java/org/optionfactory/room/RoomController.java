package org.optionfactory.room;

import org.optionfactory.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomFacade roomFacade;

    @GetMapping("/listPublic")
    public List<Room> listPublicRooms(@RequestParam int recordPerPage, @RequestParam int pageNumber) {
        return roomFacade.listPublic(recordPerPage, pageNumber);
    }
}
