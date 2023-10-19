package org.optionfactory.room;

import org.optionfactory.author.Author;
import org.optionfactory.author.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    @Autowired
    private RoomFacade roomFacade;
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/listPublic")
    public List<Room> listPublicRooms(@RequestParam int recordPerPage, @RequestParam int pageNumber) {
        return roomFacade.listPublic(recordPerPage, pageNumber);
    }

    @GetMapping("/listPrivate")
    public List<Room> listPrivateRooms(@RequestParam int recordPerPage, @RequestParam int pageNumber) {
        return roomFacade.listPrivate(recordPerPage, pageNumber);
    }

    @GetMapping("/listSubscribed")
    public List<Room> listSubscribedRooms(@RequestParam int recordPerPage, @RequestParam int pageNumber) {
        final long authorId = authorFacade.searchByName(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        return roomFacade.listSubscribed(recordPerPage, pageNumber, authorId);
    }

    @GetMapping("/listOwned")
    public List<Room> listOwnedRooms(@RequestParam int recordPerPage, @RequestParam int pageNumber) {
        final long authorId = authorFacade.searchByName(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
        return roomFacade.listOwned(recordPerPage, pageNumber, authorId);
    }
}
