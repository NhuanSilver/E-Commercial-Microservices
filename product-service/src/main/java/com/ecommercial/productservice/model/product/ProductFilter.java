/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/27/23, 4:49 PM
 *  * Last Modified: 12/27/23, 4:49 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.filter.BaseFilter;
import lombok.Data;

@Data
public class ProductFilter extends BaseFilter {
    private String name;
    private Double priceTo;
    private Double priceFrom;
    private String productId;
    private String industrialId;
    private String tradeMarkId;
}
