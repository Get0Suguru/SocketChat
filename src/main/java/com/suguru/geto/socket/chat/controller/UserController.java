package com.suguru.geto.socket.chat.controller;

import com.suguru.geto.socket.chat.payload.UserRequest;
import com.suguru.geto.socket.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String createUser(@RequestBody UserRequest userRequest) {
        return chatService.findOrCreateUser(userRequest.getUsername(), userRequest.getPassword());
    }
}