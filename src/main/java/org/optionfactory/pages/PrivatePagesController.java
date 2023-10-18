package org.optionfactory.pages;

import org.optionfactory.author.Author;
import org.optionfactory.author.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/private")
public class PrivatePagesController {
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/welcome")
    public String getWelcomePage(Model model) {
        final Author currentAuthor = authorFacade.searchByName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("author", currentAuthor);
        return "welcome";
    }
}
