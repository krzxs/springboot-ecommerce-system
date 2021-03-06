package com.bookerinio.ecommercesystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@AllArgsConstructor
@Service
public class MailBuilder {

    private final TemplateEngine templateEngine;

    public String build(String username, String tokenUrl) {
        Context context = new Context();
        context.setVariable("username", username);
        context.setVariable("tokenUrl", tokenUrl);
        return templateEngine.process("mailTemplate", context);
    }
}
