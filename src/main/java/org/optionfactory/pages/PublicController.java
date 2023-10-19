package org.optionfactory.pages;

import org.optionfactory.author.Author;
import org.optionfactory.author.AuthorFacade;
import org.optionfactory.author.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/rooms")
    public String getPublicRoomsPage(Model model) {
        final String currentAuthorName = SecurityContextHolder.getContext().getAuthentication().getName();
        Author currentAuthor;
        try {
            currentAuthor = authorFacade.searchByName(currentAuthorName);
        } catch (AuthorNotFoundException e) {
            currentAuthor = null;
        }
        model.addAttribute("author", currentAuthor);
        return "rooms";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
