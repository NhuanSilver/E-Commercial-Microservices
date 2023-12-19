/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:59 PM
 *  * Last Modified: 12/18/23, 8:59 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.industrial;

import com.ecommercial.productservice.base.model.BaseModel;
import lombok.Data;

@Data

public class IndustrialProduct extends BaseModel {
    private String name;
    private String iconUrl;
    private String description;
}
