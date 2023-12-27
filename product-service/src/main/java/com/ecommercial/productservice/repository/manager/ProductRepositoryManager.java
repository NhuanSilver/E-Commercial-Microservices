/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:07 PM
 *  * Last Modified: 12/26/23, 11:07 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.repository.manager;

import com.ecommercial.productservice.base.filter.ResultList;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductFilter;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;

import java.util.List;

public interface ProductRepositoryManager {
    ProductDetail createProduct(Product product, List<ProductVariant> productVariants);

    ProductDetail getProductDetailById(String id);

    ProductVariant getProductVariantById(String variantId);

    MongoCollection getCollection();
}
