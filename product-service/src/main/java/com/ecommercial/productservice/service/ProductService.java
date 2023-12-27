/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:10 PM
 *  * Last Modified: 12/26/23, 11:10 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ProductService {

    ProductDetail createProduct(CreateProductInput productInput) throws ProductServiceException;

    ProductDetail getProductDetailById(String id) throws ProductServiceException;

    Product getProductById(String id) throws ProductServiceException;

    Product updateProduct(String id, UpdateProductInput updateProductInput);

    void deleteProduct(String id);

    ResponseEntity<Map<String, Object>> pagingProduct(String name, int page, int size) throws ProductServiceException;

    List<Product> getAllProduct() throws ProductServiceException;

    ProductVariant getProductVariantById(String variantId) throws ProductServiceException;
}
