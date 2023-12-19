/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:56 PM
 *  * Last Modified: 12/18/23, 8:56 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement;

import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.repository.ProductRepository;
import com.ecommercial.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product, ProductVariant productVariant) {
        return null;
    }

    @Override
    public Product getProductById(String id) {
        return null;
    }

    @Override
    public ProductDetail getProductDetailById(String id) {
        return null;
    }

    @Override
    public Product updateProduct(String id, UpdateProductInput updateProductInput) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }
}
