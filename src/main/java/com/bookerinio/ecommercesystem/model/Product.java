package com.bookerinio.ecommercesystem.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product implements Serializable {

    private String uniqueId;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private int availableItemCount;
    private ProductCategory category;
    private List<ProductReview> productReview;
}
