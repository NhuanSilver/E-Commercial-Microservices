/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 9:11 PM
 *  * Last Modified: 12/18/23, 9:11 PM
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

}
