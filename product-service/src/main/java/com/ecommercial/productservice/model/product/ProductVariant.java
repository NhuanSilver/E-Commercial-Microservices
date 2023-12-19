package com.ecommercial.productservice.model.product;

import com.ecommercial.productservice.base.model.BaseModel;
import com.ecommercial.productservice.base.model.MoneyV2;
import com.ecommercial.productservice.enums.ColorProduct;
import com.ecommercial.productservice.enums.SizeType;
import com.ecommercial.productservice.model.size.DimensionMeasurement;
import com.ecommercial.productservice.model.weight.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(value = "productVariants")
@NoArgsConstructor
@Builder
public class ProductVariant extends BaseModel {
    private String imageUrl;
    private MoneyV2 price;
    private String productId;
    private String productName;
    private Integer quantityAvailable;
    private boolean requiresShipping;
    private String sku;
    private String title;
    private ColorProduct color;
    private SizeType size;
    private Double weight;
    private WeightUnit weightUnit;
    private DimensionMeasurement dimension;
    private MoneyV2 salePrice;
    private double discount;
}