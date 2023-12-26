package com.ecommercial.cart.dto.product;

import com.ecommercial.cart.dto.money.Money;
import lombok.Data;

@Data
public class ProductVariant {
    private Long id;
    private String imageUrl;
    private Money price;
    private Long productId;
    private String productName;
    private Integer quantityAvailable;
    private boolean requiresShipping;
    private String sku;
    private String title;
    private String color;
    private String size;
    private Double weight;
    private String weightUnit;
    private DimensionMeasurement dimension;
    private Money salePrice;
    private double discount;
}
