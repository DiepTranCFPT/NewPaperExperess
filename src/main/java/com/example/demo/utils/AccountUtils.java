package com.example.demo.utils;

import com.example.demo.entity.User;
import com.example.demo.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountUtils {
    @Autowired
    AuthenticationRepository userRepository;

    public Optional<User> getCurrentUser(){
        String email=  SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }
}
