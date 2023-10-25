package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/v1/author")
public class AuthorAdminController {
    @Autowired
    private AuthorFacade authorFacade;

    @GetMapping("/search")
    public BootstrapTableAuthorList listAuthors(@RequestParam String search, @RequestParam int offset, @RequestParam int limit) {
        return authorFacade.listByName(search, offset, limit);
    }

    @PostMapping("/{id}/edit")
    public void updateAuthor(@PathVariable long id, String privilege, boolean isBlocked) {
        authorFacade.updateById(id, privilege, isBlocked);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> authorNotFound(AuthorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.message);
    }
}
