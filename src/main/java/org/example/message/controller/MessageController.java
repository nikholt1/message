package org.example.message.controller;

import org.example.message.model.Message;
import org.example.message.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
// Controller-klassen håndterer HTTP-anmodninger fra klienten.
// Den bruger @Controller i stedet for @RestController og returnerer data via ResponseEntity.

public class MessageController {
    private final MessageService service;
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.service = messageService;
        this.messageService = messageService;
    }

    // HTML view endpoint
    @GetMapping("/view/{id}")
    public String showHomePage(@PathVariable int id,
                               @RequestParam(required = false) String caps,
                               Model model) {
        Message message = service.findMessageById(id, caps);
        model.addAttribute("id", message.getId());
        model.addAttribute("content", message.getContent());
        return "index";
    }

    @GetMapping("/viewAll")
    public String showAllPage(Model model) {
        model.addAttribute("list", service.getMessages());
        return "allPage";
    }


    // REST endpoint returning JSON list
    @GetMapping("/message")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = service.getMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    // REST endpoint returning JSON by id
    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id,
                                                  @RequestParam(required = false) String caps) {
        Message message = service.findMessageById(id, caps);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        Message messageSend = service.addMessage(message);
        return new ResponseEntity<>(messageSend, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Message> updateMessage(@RequestBody String name, @RequestBody String description) {

    }

    @PostMapping("addMessage")
    public ResponseEntity<Message> addMessage2(@RequestBody Message message) {
        Message messageSend = service.addMessage(message);
        return new ResponseEntity<>(messageSend, HttpStatus.OK);

    }
}
