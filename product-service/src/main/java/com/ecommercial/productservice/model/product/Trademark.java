/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 9:08 PM
 *  * Last Modified: 12/18/23, 9:08 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import lombok.Data;


@Data
public class Trademark extends BaseModel {
    private String industrialId;
    private String name;
    private String description;
    private String iconUrl;
}