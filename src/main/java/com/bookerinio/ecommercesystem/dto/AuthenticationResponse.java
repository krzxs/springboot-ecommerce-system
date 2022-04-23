package com.bookerinio.ecommercesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    private String accessToken;
    private String username;
}
