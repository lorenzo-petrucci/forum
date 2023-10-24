package org.optionfactory.room;

import org.optionfactory.author.AuthorDetails;
import org.optionfactory.author.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/v1/room")
public class RoomPrivateController {
    @Autowired
    private RoomFacade roomFacade;
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/list")
    public List<Room> privateListRooms(
            @RequestParam int recordPerPage,
            @RequestParam int pageNumber,
            @RequestParam RoomType roomType,
            @AuthenticationPrincipal AuthorDetails authorDetails) {
        return roomFacade.list(recordPerPage, pageNumber, authorDetails.getId(), roomType);
    }

    @PostMapping("/create")
    public void createRoom(RoomRequest roomRequest, @AuthenticationPrincipal AuthorDetails authorDetails) {
        roomFacade.upsert(roomRequest, authorDetails.getId());
    }
}
