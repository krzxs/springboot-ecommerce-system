package com.bookerinio.ecommercesystem.dto;

import lombok.Data;

@Data
public class AccountUpdateRequest {

    private String username;
    private String password;
    private String phone;
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String country;
}
