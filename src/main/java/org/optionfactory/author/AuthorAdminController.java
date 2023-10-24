package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/api/v1/author")
public class AuthorAdminController {
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/search")
    public List<Author> listAuthors(@RequestParam String name) {
        return authorFacade.listByName(name);
    }

    @PostMapping("/block")
    public void updateAuthorBlockStatus(@RequestParam long id, @RequestParam boolean block) {
        authorFacade.updateBlockStatusById(id, block);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> authorNotFound(AuthorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.message);
    }
}
