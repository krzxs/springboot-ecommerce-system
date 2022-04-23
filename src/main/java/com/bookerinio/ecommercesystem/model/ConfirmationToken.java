package com.bookerinio.ecommercesystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "confirmation_token")
public class ConfirmationToken {

    @Id
    private String id;
    private String token;
    private LocalDateTime expiresAt;
    private User user;

    public ConfirmationToken(String token, LocalDateTime expiresAt, User user) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }
}
