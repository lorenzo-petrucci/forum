package org.optionfactory.author;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
    public BootstrapTableAuthorList listAuthors(@RequestParam String search, @RequestParam int offset, @RequestParam int limit) {
        return authorFacade.listByName(search, offset, limit);
    }

    @PostMapping("/{id}/edit")
    public void updateAuthorBlockStatus(@PathVariable long id, @RequestParam boolean block) {
        authorFacade.updateBlockStatusById(id, block);
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> authorNotFound(AuthorNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.message);
    }
}
