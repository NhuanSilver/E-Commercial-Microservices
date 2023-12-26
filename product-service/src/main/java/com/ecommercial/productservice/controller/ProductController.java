/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 1:32 PM
 *  * Last Modified: 12/26/23, 1:32 PM
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
        validateProductVariantInput(productInput);
        return productService.createProduct(productInput);
    }

    @Operation(summary = "Get Product By Product Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable String id) throws ProductServiceException {
        Product product = productService.getProductById(id);
        if (null == product) {
            throw new ProductServiceException("not_found", "Không tìm thấy sản phẩm", "Product not found");
        }
        return productService.getProductById(id);
    }

    @Operation(summary = "Get Product Detail By Product Id")
    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDetail getProductDetail(@PathVariable String id) throws ProductServiceException {
        ProductDetail productDetail = productService.getProductDetailById(id);
        if (null == productDetail) {
            throw new ProductServiceException("not_found", "Không tìm thấy sản phẩm", "Product not found");
        }
        return productService.getProductDetailById(id);
    }

    @Operation(summary = "Get All Product")
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProduct() throws ProductServiceException {
        List<Product> products = productService.getAllProduct();
        if (null == products) {
            throw new ProductServiceException("not_found", "Không tìm thấy sản phẩm", "Product not found");
        }
        return productService.getAllProduct();
    }


    //    validate input

    private void validateProductVariantInput(CreateProductInput productInput) throws ProductServiceException {
        if (null == productInput) {
            throw new ProductServiceException("not_found", "Vui lòng nhập thông tin của sản phẩm biến thể", "Product variant is empty");
        }
        if (null == productInput.getProduct()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập thông tin của sản phẩm", "Product is empty");
        }
        if (null == productInput.getProduct().getDescription()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập mô tả của sản phẩm", "Product description is empty");
        }
        if (null == productInput.getProduct().getFeaturedImageUrl()) {
            throw new ProductServiceException("not_found", "Vui lòng nhập hình ảnh mô tả của sản phẩm", "Product featured image url is empty");

        }
        if (null == productInput.getProduct().getIndustrialId()) {
            throw new ProductServiceException("not_found", "Vui lòng chọn nghành hàng của sản phẩm", "Product industrial type is empty");

        }
        if (null == productInput.getProduct().getTradeMarkId()) {
            throw new ProductServiceException("not_found", "Vui lòng chọn thương hiệu của sản phẩm", "Trade mark is empty");
        }
        if (null == productInput.getProduct().getName()) {
            throw new ProductServiceException("not_found", "Vui lòng tên của sản phẩm", "Product name is empty");

        }


        validateVariants(productInput.getProductVariants());
    }

    private void validateVariants(List<ProductVariant> productVariantList) throws ProductServiceException {
        for (ProductVariant variant : productVariantList) {
            if (null == variant)
                throw new ProductServiceException("not_found", "Vui lòng truyền thông tin biến thể của sản phầm", "Product variant is empty");
            if (null == variant.getColor()) {
                throw new ProductServiceException("not_found", "Vui lòng chọn màu của sản phầm", "Product variant color is empty");
            }
            if (null == variant.getPrice() || variant.getPrice().getAmount() <= 0) {
                throw new ProductServiceException("not_found", "Vui lòng nhập giá của sản phầm", "Product variant price is empty");
            }
            if (variant.getWeight() <= 0 || variant.getWeight() > 1600000) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập khối lượng trong khoảng 0 < và < 1.600.000 gram.", "ProductVariant.weight is null or <= 0");

            }
            if (variant.getImageUrl() == null) {
                throw new ProductServiceException("invalid_data", "Vui lòng nhập hình ảnh của biến thể sản phẩm.", "ProductVariant.imageUrl is null");
            }
            if (null == variant.getDimension()) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập thông tin  kích thước.", "ProductVariant.Dimension is null");
            }
            if (variant.getDimension().getLength() <= 0 || variant.getDimension().getLength() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều dài trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.lenght is null or < 0");

            }
            if (variant.getDimension().getWidth() <= 0 || variant.getDimension().getWidth() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều rộng trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.weight is null or < 0");

            }
            if (variant.getDimension().getHeight() <= 0 || variant.getDimension().getHeight() > 200) {
                throw new ProductServiceException("invalid_data", " Vui lòng nhập chiều cao trong thông tin  kích thước > 0 và < 200 cm.", "ProductVariant.Dimension.height is null or < 0");

            }
            long quantityAvailable = variant.getQuantityAvailable();
            if (quantityAvailable <= 0) {
                throw new ProductServiceException("not_found", "Vui lòng nhập số lượng sản phẩm", "Product quantity available is empty or not positive");
            }
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
