package com.bookerinio.ecommercesystem.controller;

import com.bookerinio.ecommercesystem.dto.AccountUpdateRequest;
import com.bookerinio.ecommercesystem.dto.ApiResponse;
import com.bookerinio.ecommercesystem.model.Address;
import com.bookerinio.ecommercesystem.model.User;
import com.bookerinio.ecommercesystem.service.AuthService;
import com.bookerinio.ecommercesystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/account")
@RestController
public class AccountController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public User viewAccountDetails(@AuthenticationPrincipal User user) {
        return user;
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> saveAccountDetails(@RequestBody AccountUpdateRequest req, @AuthenticationPrincipal User loggedUser) {
        if(userService.existsByUsername(req.getUsername()) && !req.getUsername().equalsIgnoreCase(loggedUser.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(400, "username already exists"), HttpStatus.BAD_REQUEST);
        }
        //TODO BETTER UPDATE
        loggedUser.setUsername(req.getUsername());
        String encodedPassword = authService.encodePassword(req.getPassword());
        loggedUser.setPassword(encodedPassword);
        loggedUser.setPhone("" + req.getPhone());
        Address shippingAddress = loggedUser.getShippingAddress();
        shippingAddress.setStreetAddress("" + req.getStreetAddress());
        shippingAddress.setCity("" + req.getCity());
        shippingAddress.setState("" + req.getState());
        shippingAddress.setZipcode("" + req.getZipcode());
        shippingAddress.setCountry("" + req.getCountry());
        userService.save(loggedUser);
        return new ResponseEntity<>(new ApiResponse(200, "account updated successfully"), HttpStatus.OK);
    }
}
