package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @PostMapping("/create")
    public RedirectView createAuthor(AuthorRequest authorRequest) {
        authorFacade.create(authorRequest);
        final String redirectUrl = "/forum/public/login";
        return new RedirectView(redirectUrl);
    }

    @GetMapping("/read")
    public Author searchAuthor(@RequestParam String name) {
        return authorFacade.searchByName(name);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> authorNotFound(AuthorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.message);
    }
}
