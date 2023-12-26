/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 10:25 AM
 *  * Last Modified: 12/26/23, 10:24 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.model.product.Trademark;

import java.util.List;

public interface ProductTrademarkService {

    Trademark createTrademarkProduct(Trademark trademark);

    List<Trademark> getListTrademarkProduct();

    Trademark getTrademarkByIndustrialId(String industrialId);

    List<Trademark> getListTrademarkByIndustrialId(String industrialId);

    Trademark getTrademarkById(String id);

    List<Trademark> getTrademarkByIndustrials(String industrialId);
}
