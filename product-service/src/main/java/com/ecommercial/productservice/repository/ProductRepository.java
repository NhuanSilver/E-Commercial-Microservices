/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:34 PM
 *  * Last Modified: 12/26/23, 1:29 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.repository;

import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.repository.manager.ProductRepositoryManager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String>, ProductRepositoryManager {
    Product getProductById(String id);
}
