package org.optionfactory.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {
    @Autowired
    private MessageFacade messageFacade;

    @GetMapping("/list")
    public List<Message> listMessages() {
        return messageFacade.list();
    }

    @PostMapping("/create")
    public Long postMessage(@RequestBody MessageRequest messageRequest) {
        return messageFacade.create(messageRequest);
    }

    @PutMapping(value = "/edit/{id}")
    public Long editMessage() {
        return null;
    }

    @DeleteMapping(value = "/delete/{id}")
    public Long deleteMessage() {
        return null;
    }
}
