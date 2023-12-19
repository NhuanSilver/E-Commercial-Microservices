/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:54 PM
 *  * Last Modified: 12/18/23, 8:54 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductVariant;

public interface ProductVariantService {

    ProductVariant createProductVariant(ProductVariant productVariant);

    Product getProductVariantById(String id);
}
