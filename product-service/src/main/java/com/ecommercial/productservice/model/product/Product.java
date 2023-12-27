/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 12:40 PM
 *  * Last Modified: 12/26/23, 12:40 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import com.ecommercial.productservice.base.model.MoneyV2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
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
    private double discount;
    private String tradeMarkId;//thương hiệu
}
