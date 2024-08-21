package com.example.demo.service;


import com.example.demo.iservice.IEmailService;
import com.example.demo.repository.AuthenticationRepository;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.function.Function;

@Service
public class EmailService implements IEmailService {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final AuthenticationRepository authenticationRepository;
    private final JavaMailSenderImpl mailSender;

    @Autowired
    public EmailService(TemplateEngine templateEngine,
                        JavaMailSender javaMailSender,
                        AuthenticationRepository authenticationRepository, JavaMailSenderImpl mailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.authenticationRepository = authenticationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public void sendMailVerification(String subject,String email, String codeVerifi, Function<String, String> function)throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(new InternetAddress("newsexpressproject@gmail.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(subject);

        message.setContent(function.apply(codeVerifi), "text/html; charset=utf-8");
        javaMailSender.send(message);
    }
}
