package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public/api/v1/author")
public class AuthorPublicController {
    @Autowired
    private AuthorFacade authorFacade;

    @PostMapping("/create")
    public void createAuthor(AuthorRequest authorRequest) {
        authorFacade.create(authorRequest);
    }

    @ExceptionHandler(AuthorAlreadyExistsException.class)
    public ResponseEntity<String> authorAlreadyExists(AuthorAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.message);
    }

    @ExceptionHandler(AuthorIsEmptyException.class)
    public ResponseEntity<String> authorIsEmpty(AuthorIsEmptyException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.message);
    }
}
