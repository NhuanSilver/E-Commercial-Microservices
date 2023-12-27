package com.ecommercial.cart.proxy;

import com.ecommercial.cart.dto.product.Product;
import com.ecommercial.cart.dto.product.ProductVariant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductProxy {
    @GetMapping("/api/v1/products/variant/{id}")
    ProductVariant getProductVariantById(@PathVariable Long id);

    @GetMapping("/api/v1/products/detail/{id}")
    Product getProductDetail(@PathVariable String id);
}
