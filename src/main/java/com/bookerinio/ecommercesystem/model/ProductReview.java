package com.bookerinio.ecommercesystem.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    private String uniqueId;
    private int rating;
    private String review;

}
