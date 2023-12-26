/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:33 PM
 *  * Last Modified: 12/26/23, 1:33 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement.serivce;

import com.ecommercial.productservice.base.mongo.BaseRepository;
import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.model.size.DimensionUnit;
import com.ecommercial.productservice.model.weight.WeightUnit;
import com.ecommercial.productservice.repository.ProductRepository;
import com.ecommercial.productservice.service.ProductService;
import com.ecommercial.productservice.utils.GeneralIdUtils;
import com.ecommercial.productservice.utils.MoneyCalculateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseRepository implements ProductService {
    @Autowired
    private final ProductRepository productRepository;
    @Override
    public ProductDetail createProduct(CreateProductInput productInput) {
        Product product = productInput.getProduct();
        product.setId(generateId());
        product.setCreatedAt(new Date());

        double mediumPrice = 0;
        List<ProductVariant> productVariant = productInput.getProductVariants();
        for (ProductVariant variant : productVariant) {
            variant.setId(GeneralIdUtils.generateId());
            variant.setProductId(product.getId());
            variant.setProductName(product.getName());
            variant.setCreatedAt(new Date());
            variant.setWeightUnit(WeightUnit.GRAMS);
            variant.getDimension().setDimensionUnit(DimensionUnit.CM);
            variant.setSalePrice(MoneyCalculateUtils.calculateDiscount(variant.getPrice().getAmount(), variant.getDiscount()));
            mediumPrice += variant.getPrice().getAmount();

        }
        mediumPrice = mediumPrice / productVariant.size();

        product.setMediumPrice(MoneyCalculateUtils.getMoney(mediumPrice));
        product.setSalePrice(MoneyCalculateUtils.calculateDiscount(mediumPrice, product.getDiscount()));

        return productRepository.createProduct(product, productVariant);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    @Override
    public ProductDetail getProductDetailById(String id) {
        return productRepository.getProductDetailById(id);
    }

    @Override
    public Product updateProduct(String id, UpdateProductInput updateProductInput) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<Product> pagingProduct(int page, int size) {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}
