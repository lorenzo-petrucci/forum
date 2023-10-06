package org.optionfactory.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    @Autowired
    private MessageFacade messageFacade;

    @GetMapping("/list/{threadId}")
    public List<Message> listMessages(@PathVariable Long threadId) {
        return messageFacade.list(threadId);
    }

    @PostMapping("/create")
    public Long createMessage(@RequestBody MessageRequest messageRequest) {
        return messageFacade.create(messageRequest);
    }

    @PutMapping(value = "/edit/{id}")
    public void editMessage(@PathVariable Long id, @RequestBody MessageRequest messageRequest) {
        messageFacade.update(id, messageRequest);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteMessage(@PathVariable Long id, @RequestBody MessageRequest messageRequest) {
        messageFacade.delete(id, messageRequest);
    }
}
