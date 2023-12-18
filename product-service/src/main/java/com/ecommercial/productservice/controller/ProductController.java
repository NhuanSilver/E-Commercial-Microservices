package com.ecommercial.productservice.controller;



import com.ecommercial.productservice.dto.product.ProductRequest;
import com.ecommercial.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController  {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Check Product Api")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String checkProductApi() {
        return "Product Api is working";
    }

    @Operation(summary = "Create Product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @Operation(summary = "Get All Products")
    @GetMapping("/all")
    public void getAllProducts() {
        productService.getAllProducts();
    }


}
