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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product/industrial")
@RequiredArgsConstructor
public class ProductIndustrialController extends MainController {

    @Autowired
    private ProductIndustrialService productIndustrialService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String checkProductApi() {
        return "Product Api is working";
    }

    @Operation(summary = "Get All Industrial Product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<IndustrialProduct> getAllIndustrial() {
        return productIndustrialService.getListIndustrial();
    }

    @Operation(summary = "Created Industrial Product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IndustrialProduct createIndustrial(@RequestBody IndustrialProduct industrialProductRequest) throws ProductServiceException {
        validateIndustrialProductInput(industrialProductRequest);
        return productIndustrialService.createIndustrialProduct(industrialProductRequest);
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
