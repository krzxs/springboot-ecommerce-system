package com.bookerinio.ecommercesystem.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final MailBuilder mailBuilder;

    @Async
    public void sendMail(String to, String username, String tokenUrl) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            messageHelper.setFrom("test@test.com");
            messageHelper.setTo(to);
            messageHelper.setSubject("Account Activation");
            messageHelper.setText(mailBuilder.build(username, tokenUrl), true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch(MailException e) {
            throw new IllegalStateException("Error occurred when sending mail to " + to);
        }
    }
}
