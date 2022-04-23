package com.bookerinio.ecommercesystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    private String uniqueId;
    private List<Item> shoppingCartItems;
    private BigDecimal totalPrice;
    private String username;
}
