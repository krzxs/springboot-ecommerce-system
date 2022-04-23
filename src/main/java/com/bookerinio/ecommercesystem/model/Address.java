package com.bookerinio.ecommercesystem.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String streetAddress = "";
    private String city = "";
    private String state = "";
    private String zipcode = "";
    private String country = "";
}
