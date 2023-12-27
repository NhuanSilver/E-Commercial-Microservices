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
import com.ecommercial.productservice.model.product.Trademark;
import com.ecommercial.productservice.repository.ProductTrademarkRepository;
import com.ecommercial.productservice.service.ProductTrademarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductTrademarkServiceImpl extends BaseRepository implements ProductTrademarkService {

    private final ProductTrademarkRepository trademarkRepository;

    @Override
    public Trademark createTrademarkProduct(Trademark trademark) throws ProductServiceException {
        validateTrademarkProductInput(trademark);
        trademark.setId(generateId());
        trademark.setCreatedAt(new Date());
        trademark.setUpdatedAt(null);
        trademarkRepository.save(trademark);
        return trademark;
    }

    @Override
    public List<Trademark> getListTrademarkProduct() {
        return trademarkRepository.findAll();
    }

    @Override
    public Trademark getTrademarkByIndustrialId(String industrialId) throws ProductServiceException {
        Trademark trademark = trademarkRepository.findByIndustrialId(industrialId);
        if (null == trademark) {
            throw new ProductServiceException("not_found", "Không tìm thấy thương hiệu nào", "Not found data trademark by industrial id:" + industrialId);
        }
        return trademark;

    }

    @Override
    public List<Trademark> getListTrademarkByIndustrialId(String industrialId) {
        return trademarkRepository.findAllById(List.of(industrialId));
    }

    @Override
    public Trademark getTrademarkById(String trademarkId) throws ProductServiceException {
        Trademark trademark = trademarkRepository.findById(trademarkId).orElse(null);
        if (null == trademark)
            throw new ProductServiceException("not_found", "Không tìm thấy thông tin thương hiệu", "Not found data trademark by id: " + trademarkId);
        return trademark;
    }

    @Override
    public List<Trademark> getTrademarkByIndustrials(String industrialId) {
        return trademarkRepository.findAllById(List.of(industrialId));
    }

    //    validate segment
    private void validateTrademarkProductInput(Trademark trademark) throws ProductServiceException {
        if (null == trademark) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền thông tin thương hiệu", "Trademark product is null");
        }
        if (null == trademark.getName()) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền tên thương hiệu", "Trademark product name is null");
        }
        if (null == trademark.getIndustrialId()) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền mã nghành hàng của thương hiệu", "Trademark product industrial id is null");
        }
        if (null == trademark.getIconUrl()) {
            throw new ProductServiceException("invalid_data", "Vui lòng truyền icon của thương hiệu", "Trademark product icon url is null");
        }
    }
}
