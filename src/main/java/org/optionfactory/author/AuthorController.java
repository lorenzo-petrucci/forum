package org.optionfactory.author;

import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @PostMapping("/create")
    public RedirectView create(AuthorRequest authorRequest) {
        final long authorId = authorFacade.create(authorRequest);
        final String redirectUrl = "/forum/public/login";
        return new RedirectView(redirectUrl);
    }

    @GetMapping("/read")
    public Author read(@RequestParam String name) {
        return authorFacade.searchByName(name);
    }
}
