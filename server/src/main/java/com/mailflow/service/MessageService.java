package com.mailflow.service;

import com.mailflow.model.Message;
import com.mailflow.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public Message sendMessage(Message message) {
        return repo.save(message);
    }

    public List<Message> getInbox(String user) {
        return repo.findByReceiver(user);
    }

    public List<Message> getSent(String user) {
        return repo.findBySender(user);
    }
}