package org.optionfactory;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class ForumController {

    @GetMapping("/")
    public void getMessage(@RequestParam(required = false, name = "message") String message) {
        System.out.println(message);
    }

    @PostMapping("/")
    public void postMessage(@RequestBody String request) {
        System.out.println(request);
    }
}
