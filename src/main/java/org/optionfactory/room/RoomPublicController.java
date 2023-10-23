package org.optionfactory.room;

import org.optionfactory.author.AuthorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public/api/v1/room")
public class RoomPublicController {
    @Autowired
    private RoomFacade roomFacade;

    @GetMapping("/list")
    public List<Room> publicListRooms(
            @RequestParam int recordPerPage,
            @RequestParam int pageNumber) {
        return roomFacade.listPublic(recordPerPage, pageNumber);
    }
}
