package com.medical.demo.controller;
import static com.medical.demo.UrlMapping.*;
import com.medical.demo.model.Chat;
import com.medical.demo.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(Chat)
public class ChatController {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping()
    public List<Chat> getAllMessages() {
        List<Chat> chats = chatRepository.findAll();
        return chats;
    }

    @PostMapping()
    public Chat sendMessage(@RequestBody Chat chat) {
        chat.setTime(LocalDateTime.now());
        Chat savedChat = chatRepository.save(chat);
        return savedChat;
    }
}
