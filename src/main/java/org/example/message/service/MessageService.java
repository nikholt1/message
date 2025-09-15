package org.example.message.service;

import org.example.message.model.Message;
import org.example.message.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// Service-laget indeholder forretningslogik og kalder repository-klassen.
// Repository injiceres via konstruktøren (dependency injection).
public class MessageService {
    private final MessageRepository repository;
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository repository, MessageRepository messageRepository) {
        this.repository = repository;
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages() {
        return repository.getAllMessages();
    }

    public Message findMessageById(int id, String caps) {
        Message message = repository.findMessageById(id);
        if (caps.equals("yes")) {
            Message newMessage = new Message(id, message.getContent().toUpperCase());
            return newMessage;
        } else {
            return message;
        }
    }
    public Message updateMessage(int id, String description) {
        Message message = repository.findMessageById(id);

        message.setContent();
        return message;
    }

    public Message addMessage(Message message) {
        repository.addMessage(message);
        return message;
    }
}
