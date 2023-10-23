package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("private/api/v1/author")
public class AuthorPrivateController {
    @Autowired
    private AuthorFacade authorFacade;

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
