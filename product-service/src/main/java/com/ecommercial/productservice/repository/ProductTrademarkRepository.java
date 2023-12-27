/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 9:21 PM
 *  * Last Modified: 12/18/23, 9:21 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.repository;

import com.ecommercial.productservice.model.product.Trademark;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ProductTrademarkRepository extends MongoRepository<Trademark, String> {
    Trademark findByIndustrialId(String industrialId);
}
