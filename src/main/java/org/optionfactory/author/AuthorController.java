package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
public class AuthorController {
    @Autowired
    private AuthorFacade authorFacade;

    @PostMapping("/create")
    public Long create(@RequestParam String name, @RequestParam String password) {
        return authorFacade.create(new AuthorRequest(name, password));
    }

    @GetMapping("/read")
    public Author read(@RequestParam String name, @RequestParam String password) {
        return authorFacade.read(new AuthorRequest(name, password)).orElseThrow();
    }
}
