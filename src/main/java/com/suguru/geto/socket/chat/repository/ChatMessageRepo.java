package com.suguru.geto.socket.chat.repository;

import com.suguru.geto.socket.chat.model.ChatGroup;
import com.suguru.geto.socket.chat.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByGroup(ChatGroup group);
}
