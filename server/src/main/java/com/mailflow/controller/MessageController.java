package com.mailflow.controller;

import com.mailflow.model.Message;
import com.mailflow.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping
    public Message send(@RequestBody Message message) {
        return service.sendMessage(message);
    }

    @GetMapping("/inbox/{user}")
    public List<Message> inbox(@PathVariable String user) {
        return service.getInbox(user);
    }

    @GetMapping("/sent/{user}")
    public List<Message> sent(@PathVariable String user) {
        return service.getSent(user);
    }

    @GetMapping("/summary/{id}")
    public Map<String, String> summary(@PathVariable String id) {
        Message message = service.getMessageById(id);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found.");
        }
        return Map.of("summary", service.generateSummary(message));
    }

    @GetMapping("/reply-suggestion/{id}")
    public Map<String, String> replySuggestion(@PathVariable String id, @RequestParam String userEmail) {
        Message message = service.getMessageById(id);
        if (message == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message not found.");
        }
        return Map.of("reply", service.generateReplySuggestion(message, userEmail));
    }
}