package com.bookerinio.ecommercesystem.service;

import com.bookerinio.ecommercesystem.model.ConfirmationToken;
import com.bookerinio.ecommercesystem.model.User;
import com.bookerinio.ecommercesystem.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getTokenByUser(User user) {
        return confirmationTokenRepository.findByUser(user);
    }

    public Optional<ConfirmationToken> getToken(String name) {
        return confirmationTokenRepository.findByToken(name);
    }

    public void deleteTokenByUser(User user) {
        confirmationTokenRepository.deleteByUser(user);
    }
}
