package com.bookerinio.ecommercesystem.controller;

import com.bookerinio.ecommercesystem.dto.ApiResponse;
import com.bookerinio.ecommercesystem.dto.AuthenticationResponse;
import com.bookerinio.ecommercesystem.dto.LoginRequest;
import com.bookerinio.ecommercesystem.dto.RegisterRequest;
import com.bookerinio.ecommercesystem.model.User;
import com.bookerinio.ecommercesystem.service.AuthService;
import com.bookerinio.ecommercesystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(authService.logUser(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest registerRequest) {
        if(!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
            return new ResponseEntity<>(new ApiResponse(400, "password and password confirm are different"), HttpStatus.BAD_REQUEST);
        }
        if(userService.existsByUsernameOrEmail(registerRequest.getUsername(), registerRequest.getEmail())) {
            if(!userService.existsByUsername(registerRequest.getUsername())) {
                return new ResponseEntity<>(new ApiResponse(400, "account already exists"), HttpStatus.BAD_REQUEST);
            }
            User user = userService.loadUser(registerRequest.getUsername());
            if(user.getEmail().equalsIgnoreCase(registerRequest.getEmail())) {
                if(user.isEnabled()) {
                    return new ResponseEntity<>(new ApiResponse(400, "account already exists"), HttpStatus.BAD_REQUEST);
                }
                authService.sendToken(user);
                return new ResponseEntity<>(new ApiResponse(200, "token sent again"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse(400, "account already exists"), HttpStatus.BAD_REQUEST);
        }
        authService.registerUser(registerRequest);
        return new ResponseEntity<>(new ApiResponse(200, "user registration completed"), HttpStatus.OK);
    }

    @GetMapping("/confirm")
    public void confirm(@RequestParam("token") String token) {
        authService.confirmToken(token);
    }
}
