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
import com.ecommercial.productservice.base.filter.ResultList;
import com.ecommercial.productservice.model.input.CreateProductInput;
import com.ecommercial.productservice.model.input.UpdateProductInput;
import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductDetail;
import com.ecommercial.productservice.model.product.ProductFilter;
import com.ecommercial.productservice.model.product.ProductVariant;
import com.ecommercial.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController extends MainController {

    private final ProductService productService;

    //    api segment
    @Operation(summary = "create product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetail createProduct(@RequestBody CreateProductInput productInput) throws ProductServiceException {
        return productService.createProduct(productInput);
    }

    @Operation(summary = "get product by product id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String id) throws ProductServiceException {
        return productService.getProductById(id);
    }

    @Operation(summary = "get product detail by product id")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetail getProductDetail(@PathVariable String id) throws ProductServiceException {
        return productService.getProductDetailById(id);
    }

    @Operation(summary = "get list product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct() throws ProductServiceException {
        return productService.getAllProduct();
    }

    @Operation(summary = "get product variant by id")
    @GetMapping("/variant/{variantId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductVariant getProductVariant(@PathVariable String variantId) throws ProductServiceException {
        return productService.getProductVariantById(variantId);
    }

    @Operation(summary = "get list product by page")
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getPagingProduct(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) throws ProductServiceException {
        return productService.pagingProduct(name, page, size);

    }

    @Operation(summary = "find product by filter")
    @PostMapping("/product/filter")
    public ResultList<Product> searchProduct(@RequestBody ProductFilter productFilter) {
        return productService.filterProduct(productFilter);
    }

    @Operation(summary = "update product")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable String id, @RequestBody UpdateProductInput product) {
        return productService.updateProduct(id, product);
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
