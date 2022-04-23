package com.bookerinio.ecommercesystem.service;

import com.bookerinio.ecommercesystem.dto.AuthenticationResponse;
import com.bookerinio.ecommercesystem.dto.LoginRequest;
import com.bookerinio.ecommercesystem.dto.RegisterRequest;
import com.bookerinio.ecommercesystem.model.ConfirmationToken;
import com.bookerinio.ecommercesystem.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private static final String AUTH_LINK = "http://localhost:8080/auth/confirm?token=";

    public void registerUser(RegisterRequest request) {
        String encodedPassword = encodePassword(request.getPassword());
        User user = new User(request.getUsername(), request.getEmail(), encodedPassword);
        userService.save(user);
        sendToken(user);
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public void sendToken(User user) {
        Optional<ConfirmationToken> oldToken = confirmationTokenService.getTokenByUser(user);
        if(oldToken.isPresent()) {
            confirmationTokenService.deleteTokenByUser(user);
        }
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now().plusMinutes(15L), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        String link = AUTH_LINK + token;
        mailService.sendMail(user.getEmail(), user.getUsername(), link);
    }

    public AuthenticationResponse logUser(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String accessToken = jwtTokenProvider.generateToken(authenticate);
        return new AuthenticationResponse(accessToken, request.getUsername());
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("token not found or expired"));
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        confirmationTokenService.deleteTokenByUser(confirmationToken.getUser());
        if(expiresAt.isBefore(LocalDateTime.now())) throw new IllegalStateException("token expired");
        enableUser(confirmationToken.getUser().getUsername());
    }

    private void enableUser(String username) {
        User user = userService.loadUser(username);
        user.setEnabled(true);
        userService.save(user);
    }
}
