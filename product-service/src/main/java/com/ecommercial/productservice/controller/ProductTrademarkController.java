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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/trademark")
@RequiredArgsConstructor
public class ProductTrademarkController extends MainController {
    @Autowired
    private ProductTrademarkService trademarkService;

    @Operation(summary = "Check Product Api")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String checkApi() {
        return "Product Api is working";
    }

    @Operation(summary = "Get All Trademark Product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Trademark> getAllTrademark() throws ProductServiceException {
        return trademarkService.getListTrademarkProduct();
    }

    @Operation(summary = "Created Trademark Product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trademark createTrademark(@RequestBody Trademark trademarkRequest) throws ProductServiceException {
        validateTrademarkProductInput(trademarkRequest);
        return trademarkService.createTrademarkProduct(trademarkRequest);
    }

    @Operation(summary = "Get Trademark by Industrial Id")
    @GetMapping("/{industrialId}/industrial")
    @ResponseStatus(HttpStatus.OK)
    public Trademark getTrademarkByIndustrialId(@PathVariable String industrialId) throws ProductServiceException {
        Trademark trademark = trademarkService.getTrademarkByIndustrialId(industrialId);
        if (null == trademark) {
            throw new ProductServiceException("not_found", "Không tìm thấy thương hiệu nào", "Not found data trademark by industrial id:" + industrialId);
        }
        return trademark;
    }

    @Operation(summary = "get trademark by industrial id")
    @GetMapping("/trademark-list/{industrialId}/industrial")
    @ResponseStatus(HttpStatus.OK)
    public List<Trademark> getTrademarkByIndustrials(@PathVariable String industrialId) {
        return trademarkService.getTrademarkByIndustrials(industrialId);
    }

    @Operation(summary = "get trademark by id")
    @GetMapping("/product/trademark/{trademarkId}")
    public Trademark getTrademark(@PathVariable String trademarkId) throws ProductServiceException {
        Trademark productTrademark = trademarkService.getTrademarkById(trademarkId);
        if (null == productTrademark)
            throw new ProductServiceException("not_found", "Không tìm thấy thông tin thương hiệu", "Not found data trademark by id: " + trademarkId);
        return productTrademark;
    }

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
