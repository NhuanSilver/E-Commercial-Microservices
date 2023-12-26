/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:35 PM
 *  * Last Modified: 12/26/23, 1:35 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import com.ecommercial.productservice.base.model.MoneyV2;
import com.ecommercial.productservice.enums.ColorProduct;
import com.ecommercial.productservice.enums.SizeType;
import com.ecommercial.productservice.model.size.DimensionMeasurement;
import com.ecommercial.productservice.model.weight.WeightUnit;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Document(value = "productVariants")
@NoArgsConstructor
@Builder
public class ProductVariant extends BaseModel {
    private String imageUrl;
    private MoneyV2 price;
    private String productId;
    private String productName;
    private Integer quantityAvailable;
    private boolean requiresShipping;
    private String sku;
    private String title;
    private ColorProduct color;
    private SizeType size;
    private Double weight;
    private WeightUnit weightUnit;
    private DimensionMeasurement dimension;
    private MoneyV2 salePrice;
    private double discount;

    public static Query getQueryByProductId(String productId) {
        return new Query().addCriteria(Criteria.where("productId").is(productId));
    }
}
