package com.suguru.geto.socket.chat.repository;

import com.suguru.geto.socket.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
