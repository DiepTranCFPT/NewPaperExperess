package com.example.demo.iservice;

import jakarta.mail.MessagingException;

import java.util.function.Function;

public interface IEmailService {
    void sendMailVerification(String Subject,String email, String codeVerifi, Function<String, String> function) throws MessagingException;
}
