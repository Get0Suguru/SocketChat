package com.suguru.geto.socket.chat.service;

import com.suguru.geto.socket.chat.model.ChatGroup;
import com.suguru.geto.socket.chat.model.ChatMessage;
import com.suguru.geto.socket.chat.model.User;
import com.suguru.geto.socket.chat.payload.ChatMessageDto;
import com.suguru.geto.socket.chat.repository.ChatGroupRepo;
import com.suguru.geto.socket.chat.repository.ChatMessageRepo;
import com.suguru.geto.socket.chat.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ChatService {


    private ChatMessageRepo messageRepository;
    private UserRepo userRepository;
    private ChatGroupRepo groupRepository;

    public ChatService(ChatMessageRepo messageRepository, UserRepo userRepository, ChatGroupRepo groupRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

//     saving msg in db
    public ChatMessage saveMessage(ChatMessageDto messageDto) {
        ChatGroup group = groupRepository.findByName(messageDto.getGroupName());
        if (group == null) {
            throw new RuntimeException("Group not found with name: " + messageDto.getGroupName());
        }

        ChatMessage message = new ChatMessage();

        message.setSender(messageDto.getSender());
        message.setGroup(group);
        message.setUser(userRepository.findByUsername(messageDto.getSender()));
        message.setContent(messageDto.getContent());
        message.setSentAt(LocalDateTime.now());

        messageRepository.save(message);

        return message;
    }

    public String findOrCreateUser(String username, String password) { // Updated to include password
        User user = userRepository.findByUsername(username);

        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password); // Store password as plain text
            userRepository.save(user);

            return "created";
        } else if (user.getPassword().equals(password)) {
            return "found";
        }else {
            return "wrong";
        }
    }
    @Transactional
    public ChatGroup findOrCreateGroup(String groupName) {
        ChatGroup group = groupRepository.findByName(groupName);

        if (group == null) {
            group = new ChatGroup();
            group.setName(groupName);
            return groupRepository.save(group);
        }

        return groupRepository.save(group);

    }




}
