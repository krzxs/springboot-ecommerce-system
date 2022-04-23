package com.bookerinio.ecommercesystem.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    private Product product;
    private int quantity;
    private BigDecimal totalPrice;
}
