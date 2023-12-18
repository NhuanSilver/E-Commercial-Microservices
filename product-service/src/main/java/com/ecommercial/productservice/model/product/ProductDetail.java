package com.ecommercial.productservice.model.product;

import lombok.Data;

import java.util.List;


@Data
public class ProductDetail {
    private Product product;
    private List<ProductVariant> variants;
}
