/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:14 PM
 *  * Last Modified: 12/26/23, 11:14 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.controller;


import com.ecommercial.productservice.base.exception.ProductServiceException;
import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController extends MainController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Check Product Api")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String checkProductApi() {
        return "Product Api is working";
    }


    //    api segment
    @Operation(summary = "Create Product ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetail createProduct(@RequestBody CreateProductInput productInput) throws ProductServiceException {
        return productService.createProduct(productInput);
    }

    @Operation(summary = "Get Product By Product Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String id) throws ProductServiceException {
        return productService.getProductById(id);
    }

    @Operation(summary = "Get Product Detail By Product Id")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetail getProductDetail(@PathVariable String id) throws ProductServiceException {
        return productService.getProductDetailById(id);
    }

    @Operation(summary = "Get All Product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct() throws ProductServiceException {
        return productService.getAllProduct();
    }

    @Operation(summary = "Get Product Variant By Product Variant Id")
    @GetMapping("/variant/{variantId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductVariant getProductVariant(@PathVariable String variantId) throws ProductServiceException {
        return productService.getProductVariantById(variantId);
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
