//package com.experess.news.utils;
//
//import com.experess.news.entity.User;
//import com.experess.news.repository.AuthenticationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class AccountUtils {
//    private  final AuthenticationRepository userRepository;
//
//    @Autowired
//    public AccountUtils(AuthenticationRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public Optional<User> getCurrentUser(){
//        String email=  SecurityContextHolder.getContext().getAuthentication().getName();
//        Optional<User> user = userRepository.findByEmail(email);
//        return user;
//    }
//}
