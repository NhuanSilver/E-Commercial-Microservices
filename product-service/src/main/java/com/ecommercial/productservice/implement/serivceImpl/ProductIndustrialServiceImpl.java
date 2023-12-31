/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:43 PM
 *  * Last Modified: 12/26/23, 1:43 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement.serivceImpl;

import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.base.mongo.BaseRepository;
import com.ecommercial.productservice.model.industrial.IndustrialProduct;
import com.ecommercial.productservice.repository.ProductIndustrialRepository;
import com.ecommercial.productservice.service.ProductIndustrialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductIndustrialServiceImpl extends BaseRepository implements ProductIndustrialService {

    private final ProductIndustrialRepository productIndustrialRepository;

    @Override
    public IndustrialProduct createIndustrialProduct(IndustrialProduct industrialProduct) throws ProductServiceException {
        validateIndustrialProductInput(industrialProduct);
        industrialProduct.setId(generateId());
        industrialProduct.setCreatedAt(new Date());
        industrialProduct.setUpdatedAt(null);
        return productIndustrialRepository.save(industrialProduct);
    }

    @Override
    public List<IndustrialProduct> getListIndustrial() {
        return productIndustrialRepository.findAll();
    }

    private void validateIndustrialProductInput(IndustrialProduct industrialProduct) throws ProductServiceException {
        if (null == industrialProduct) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền thông tin nghành hàng", "Industrial product is null");
        }
        if (null == industrialProduct.getName()) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền tên nghành hàng", "Industrial product name is null");

        }
        if (null == industrialProduct.getIconUrl()) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền icon nghành hàng", "Industrial product icon url is null");
        }

    }

}
