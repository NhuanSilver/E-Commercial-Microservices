/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:07 PM
 *  * Last Modified: 12/26/23, 11:07 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement.repositoryImpl;

import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.repository.manager.ProductRepositoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryManager {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ProductDetail createProduct(Product product, List<ProductVariant> productVariants) {
        // Lưu sản phẩm chính
        mongoTemplate.save(product);

        // Lưu các biến thể của sản phẩm
        productVariants.forEach(variant -> {
            variant.setProductId(product.getId());
            mongoTemplate.save(variant);
        });
        // Tạo và trả về thông tin chi tiết sản phẩm
        ProductDetail productDetail = new ProductDetail();
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
}
