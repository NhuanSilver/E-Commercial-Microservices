package com.ecommercial.productservice.dto.product;


import com.ecommercial.productservice.base.model.MoneyV2;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private MoneyV2 price;
}
