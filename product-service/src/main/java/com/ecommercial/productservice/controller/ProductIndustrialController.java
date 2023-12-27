/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:04 AM
 *  * Last Modified: 12/26/23, 11:04 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.controller;


import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.model.industrial.IndustrialProduct;
import com.ecommercial.productservice.service.ProductIndustrialService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products/industrial")
@RequiredArgsConstructor
public class ProductIndustrialController extends MainController {

    private final ProductIndustrialService productIndustrialService;

    @Operation(summary = "get list industrial product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<IndustrialProduct> getAllIndustrial() {
        return productIndustrialService.getListIndustrial();
    }

    @Operation(summary = "create industrial product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndustrialProduct createIndustrial(@RequestBody IndustrialProduct industrialProductRequest) throws ProductServiceException {
        return productIndustrialService.createIndustrialProduct(industrialProductRequest);
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
