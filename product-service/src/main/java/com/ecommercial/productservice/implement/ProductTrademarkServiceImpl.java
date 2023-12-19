/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 9:21 PM
 *  * Last Modified: 12/18/23, 9:21 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.implement;


import com.ecommercial.productservice.base.mongo.BaseRepository;
import com.ecommercial.productservice.model.product.Trademark;
import com.ecommercial.productservice.repository.ProductTrademarkRepository;
import com.ecommercial.productservice.service.ProductTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductTrademarkServiceImpl extends BaseRepository implements ProductTrademarkService {

    private final ProductTrademarkRepository trademarkRepository;

    @Autowired
    public ProductTrademarkServiceImpl(ProductTrademarkRepository trademarkRepository) {
        this.trademarkRepository = trademarkRepository;
    }

    @Override
    public Trademark createTrademarkProduct(Trademark trademark) {
        trademark.setId(generateId());
        trademarkRepository.save(trademark);
        return trademark;
    }

    @Override
    public List<Trademark> getListTrademarkProduct() {
        return trademarkRepository.findAll();
    }

    @Override
    public Trademark getTrademarkByIndustrialId(String industrialId) {
        return trademarkRepository.findById(industrialId).orElse(null);

    }

    @Override
    public List<Trademark> getListTrademarkByIndustrialId(String industrialId) {
        return trademarkRepository.findAllById(List.of(industrialId));
    }

    @Override
    public Trademark getTrademarkById(String trademarkId) {
        return trademarkRepository.findById(trademarkId).orElse(null);
    }
}
