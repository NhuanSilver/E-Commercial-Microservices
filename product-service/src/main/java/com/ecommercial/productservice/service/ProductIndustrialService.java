/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:52 PM
 *  * Last Modified: 12/18/23, 8:52 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.model.industrial.IndustrialProduct;

public interface ProductIndustrialService {

    IndustrialProduct createIndustrialProduct(IndustrialProduct industrialProduct);

    IndustrialProduct getListIndustrial();
}
