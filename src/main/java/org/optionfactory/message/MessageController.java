package org.optionfactory.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {
    @Autowired
    private MessageFacade messageFacade;

    @GetMapping("/list/{threadId}/")
    public List<Message> listMessages(@PathVariable Long threadId) {
        return messageFacade.listByThreadId(threadId);
    }

    @DeleteMapping("/{uuid}/")
    public void deleteMessage(@PathVariable UUID uuid) {
        messageFacade.deleteByUUID(uuid);
    }

    @GetMapping("/{uuid}/")
    public Message searchMessage(@PathVariable UUID uuid) {
        return messageFacade.searchByUUID(uuid);
    }

    @PostMapping
    public void upsertMessage(@RequestBody MessageRequest messageRequest) {
        messageFacade.upsert(messageRequest);
    }

    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<Void> messageNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
