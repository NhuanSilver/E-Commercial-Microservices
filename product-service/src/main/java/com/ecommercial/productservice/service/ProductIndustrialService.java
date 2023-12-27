/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 10:14 AM
 *  * Last Modified: 12/26/23, 10:14 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.service;

import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.model.industrial.IndustrialProduct;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductIndustrialService {

    IndustrialProduct createIndustrialProduct(IndustrialProduct industrialProduct) throws ProductServiceException;

    List<IndustrialProduct> getListIndustrial();
}
