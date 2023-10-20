package org.optionfactory.pages;

import org.optionfactory.author.Author;
import org.optionfactory.author.AuthorDetails;
import org.optionfactory.author.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivateController {
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/rooms")
    public String getWelcomePage(Model model, @AuthenticationPrincipal AuthorDetails author) {
        final Author currentAuthor = authorFacade.searchByName(author.getUsername());
        model.addAttribute("author", currentAuthor);
        return "rooms";
    }
}
