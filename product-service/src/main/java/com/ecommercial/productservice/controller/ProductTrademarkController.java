/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:29 AM
 *  * Last Modified: 12/26/23, 11:29 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.controller;


import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.model.product.Trademark;
import com.ecommercial.productservice.service.ProductTrademarkService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/trademark")
@RequiredArgsConstructor
public class ProductTrademarkController extends MainController {

    private final ProductTrademarkService trademarkService;

    @Operation(summary = "get list trademark product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Trademark> getAllTrademark() throws ProductServiceException {
        return trademarkService.getListTrademarkProduct();
    }

    @Operation(summary = "create trademark product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trademark createTrademark(@RequestBody Trademark trademarkRequest) throws ProductServiceException {
        return trademarkService.createTrademarkProduct(trademarkRequest);
    }

    @Operation(summary = "get trademark by industrial id")
    @GetMapping("/{industrialId}/industrial")
    @ResponseStatus(HttpStatus.OK)
    public Trademark getTrademarkByIndustrialId(@PathVariable String industrialId) throws ProductServiceException {
        return trademarkService.getTrademarkByIndustrialId(industrialId);
    }

    @Operation(summary = "get list trademark by industrial id")
    @GetMapping("/trademark-list/{industrialId}/industrial")
    @ResponseStatus(HttpStatus.OK)
    public List<Trademark> getTrademarkByIndustrials(@PathVariable String industrialId) {
        return trademarkService.getTrademarkByIndustrials(industrialId);
    }

    @Operation(summary = "get trademark by id")
    @GetMapping("/product/trademark/{trademarkId}")
    public Trademark getTrademark(@PathVariable String trademarkId) throws ProductServiceException {
        return trademarkService.getTrademarkById(trademarkId);
    }

    //   log error

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @ExceptionHandler(ProductServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Object handleAllServiceException(ProductServiceException e) {
        LOGGER.error("ServiceException error.", e);
        return error(e.getErrorCode(), e.getErrorMessage(), e.getErrorDetail());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Object handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return error("internal_server_error", "Có lỗi trong quá trình xử lý", e.getMessage());
    }

}
