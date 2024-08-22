package com.example.demo.service;


import com.example.demo.iservice.IEmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMailVerification(String subject, String email,
                                     String codeVerifi,
                                     Function<String, String> function) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("newsexpressproject@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(subject);

        message.setContent(function.apply(email), "text/html; charset=utf-8");
        javaMailSender.send(message);
    }
}
