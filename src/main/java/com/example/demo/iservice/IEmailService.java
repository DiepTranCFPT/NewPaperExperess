package com.example.demo.iservice;

import com.example.demo.model.EmailDetail;

public interface IEmailService {
    void sendMailTemplate(EmailDetail emailDetail);
    void sendMailTemplateOwner(EmailDetail emailDetail);
    void sendMailTemplateForgot(EmailDetail emailDetail);
}
