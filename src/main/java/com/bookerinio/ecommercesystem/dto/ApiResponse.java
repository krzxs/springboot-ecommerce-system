package com.bookerinio.ecommercesystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private Integer status;
    private String message;
}
