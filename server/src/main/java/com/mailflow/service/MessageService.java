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

    public Message getMessageById(String id) {
        return repo.findById(id).orElse(null);
    }

    public String generateSummary(Message message) {
        if (message == null || message.getContent() == null || message.getContent().isBlank()) {
            return "No message content to summarize.";
        }

        String content = message.getContent().trim();
        if (content.length() <= 140) {
            return content;
        }

        int sentenceEnd = content.indexOf('.') + 1;
        if (sentenceEnd > 0 && sentenceEnd <= 140) {
            return content.substring(0, sentenceEnd).trim();
        }

        return content.substring(0, 140).trim() + "...";
    }

    public String generateReplySuggestion(Message message, String userEmail) {
        if (message == null || message.getContent() == null || message.getContent().isBlank()) {
            return "Thanks for reaching out. I will get back to you soon.";
        }

        String senderName = message.getSender() != null ? message.getSender().split("@")[0] : "there";
        String preview = message.getContent().trim();
        if (preview.length() > 120) {
            preview = preview.substring(0, 120).trim() + "...";
        }

        String userName = userEmail != null ? userEmail.split("@")[0] : "team";

        return String.format(
                "Hi %s,\n\nThanks for your message. I received your note about:\n\"%s\"\n\nI’ll review this and get back to you shortly.\n\nBest,\n%s",
                senderName,
                preview,
                userName);
    }
}
