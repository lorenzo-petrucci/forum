package org.optionfactory.pages;

import org.optionfactory.author.AuthorFacade;
import org.optionfactory.author.AuthorRequest;
import org.optionfactory.room.RoomFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("public")
public class PublicPagesController {
    @Autowired
    private RoomFacade roomFacade;
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/rooms")
    public String getRooms(Model model) {
        model.addAttribute("roomList", roomFacade.listPublicRooms());
        return "rooms";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @GetMapping("/welcome")
    public String getWelcomePage(Model model) {
        model.addAttribute("author", SecurityContextHolder.getContext().getAuthentication().getName());
        return "welcome";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
