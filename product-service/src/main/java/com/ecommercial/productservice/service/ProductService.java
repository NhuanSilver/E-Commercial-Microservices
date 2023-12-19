/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:54 PM
 *  * Last Modified: 12/18/23, 8:54 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;

public interface ProductService {

    Product createProduct(Product product, ProductVariant productVariant);

    Product getProductById(String id);

    ProductDetail getProductDetailById(String id);

    Product updateProduct(String id, UpdateProductInput updateProductInput);

    void deleteProduct(String id);

}