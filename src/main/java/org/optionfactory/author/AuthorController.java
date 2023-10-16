package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView create(AuthorRequest authorRequest) {
        final long authorId = authorFacade.create(authorRequest);
        final String redirectUrl = "/forum/public/welcome?id=" + authorId;
        return new RedirectView(redirectUrl);
    }

    @GetMapping("/read")
    public Author read(@RequestParam String name, @RequestParam String password) {
        return authorFacade.read(new AuthorRequest(name, password)).orElseThrow();
    }
}
