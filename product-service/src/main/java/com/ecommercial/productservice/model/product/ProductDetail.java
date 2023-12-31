package com.ecommercial.productservice.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProductDetail {
    private Product product;
    private List<ProductVariant> variants;
}
