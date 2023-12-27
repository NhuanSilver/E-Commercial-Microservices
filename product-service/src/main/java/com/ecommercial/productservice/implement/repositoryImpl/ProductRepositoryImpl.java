/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/27/23, 9:52 AM
 *  * Last Modified: 12/26/23, 11:07 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement.repositoryImpl;

import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.repository.manager.ProductRepositoryManager;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryManager {

    private final MongoTemplate mongoTemplate;

    @Override
    public ProductDetail createProduct(Product product, List<ProductVariant> productVariants) {
        mongoTemplate.save(product); // Lưu sản phẩm chính
        productVariants.forEach(variant -> {
            variant.setProductId(product.getId());
            mongoTemplate.save(variant);
        }); // Lưu các biến thể của sản phẩm
        ProductDetail productDetail = new ProductDetail();  // Tạo và trả về thông tin chi tiết sản phẩm
        productDetail.setProduct(product);
        productDetail.setVariants(productVariants);

        return productDetail;
    }

    @Override
    public ProductDetail getProductDetailById(String productId) {
        Product product = mongoTemplate.findById(productId, Product.class);
        List<ProductVariant> variants = mongoTemplate.find(ProductVariant.getQueryByProductId(productId), ProductVariant.class);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setVariants(variants);
        return productDetail;
    }

    @Override
    public ProductVariant getProductVariantById(String variantId) {
        return mongoTemplate.findById(variantId, ProductVariant.class);
    }

    @Override
    public MongoCollection getCollection() {
        return mongoTemplate.getCollection("products");
    }




}
