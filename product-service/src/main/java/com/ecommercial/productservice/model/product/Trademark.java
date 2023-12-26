/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:32 AM
 *  * Last Modified: 12/26/23, 11:32 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;


@EqualsAndHashCode(callSuper = true)
@Data
@Document(value = "trademarks")
public class Trademark extends BaseModel {
    private String industrialId;
    private String name;
    private String description;
    private String iconUrl;
}