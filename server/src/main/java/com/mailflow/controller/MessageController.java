package com.mailflow.controller;

import com.mailflow.model.Message;
import com.mailflow.service.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}