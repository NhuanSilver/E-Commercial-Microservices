/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:37 PM
 *  * Last Modified: 12/18/23, 8:37 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import com.ecommercial.productservice.base.model.MoneyV2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@AllArgsConstructor
@Document(value = "products")
@NoArgsConstructor
@Builder
public class Product extends BaseModel {

    private String name;
    private String industrialId;
    private String industrialTypeName;
    private String description;
    private String featuredImageUrl;
    private List<String> imageUrls;
    private MoneyV2 mediumPrice;
    private MoneyV2 salePrice;
    private String title;
    private long quantityAvailable;
    private double discount;
    private String tradeMarkId;//thương hiệu
}
