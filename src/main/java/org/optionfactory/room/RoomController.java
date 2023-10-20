package org.optionfactory.room;

import org.optionfactory.author.AuthorDetails;
import org.optionfactory.author.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomFacade roomFacade;
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/list")
    public List<Room> listOwnedRooms(
            @RequestParam int recordPerPage,
            @RequestParam int pageNumber,
            @RequestParam String roomType,
            @AuthenticationPrincipal AuthorDetails author) {
        final long authorId = author == null ? 0 : authorFacade.searchByName(author.getUsername()).getId();
        return roomFacade.list(recordPerPage, pageNumber, authorId, roomType);
    }
}
