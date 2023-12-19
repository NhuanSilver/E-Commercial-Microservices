package com.ecommercial.productservice.model.input;

import com.ecommercial.productservice.model.product.Product;
import com.ecommercial.productservice.model.product.ProductVariant;
import lombok.Data;

import java.util.List;


@Data
public class CreateProductInput {
    private Product product;
    private List<ProductVariant> productVariants;
}
