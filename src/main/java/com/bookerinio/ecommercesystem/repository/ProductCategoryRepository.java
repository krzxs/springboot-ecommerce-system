package com.bookerinio.ecommercesystem.repository;

import com.bookerinio.ecommercesystem.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, Long> {
}
