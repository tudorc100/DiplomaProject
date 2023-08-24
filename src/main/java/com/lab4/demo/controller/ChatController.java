package com.lab4.demo.controller;

import com.lab4.demo.model.Chat;
import com.lab4.demo.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    private ChatRepository messageRepository;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Chat sendMessage(@Payload Chat message) {
        System.out.println(message);
        message.setTime(LocalDateTime.now());
        messageRepository.save(message);
        return message;
    }

    // You can also add an endpoint to fetch past messages if needed.
}
