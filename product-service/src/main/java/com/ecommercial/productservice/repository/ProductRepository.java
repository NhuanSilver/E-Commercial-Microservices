package com.ecommercial.productservice.repository;

import com.ecommercial.productservice.model.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
