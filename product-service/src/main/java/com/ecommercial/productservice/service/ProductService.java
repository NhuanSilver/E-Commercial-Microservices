/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:32 PM
 *  * Last Modified: 12/26/23, 1:32 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;

import java.util.List;

public interface ProductService {

    ProductDetail createProduct(CreateProductInput productInput);

    Product getProductById(String id);

    ProductDetail getProductDetailById(String id);

    Product updateProduct(String id, UpdateProductInput updateProductInput);

    void deleteProduct(String id);

    List<Product> pagingProduct(int page, int size);

    List<Product> getAllProduct();
}
