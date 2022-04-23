package com.bookerinio.ecommercesystem.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product_category")
public class ProductCategory implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
}
