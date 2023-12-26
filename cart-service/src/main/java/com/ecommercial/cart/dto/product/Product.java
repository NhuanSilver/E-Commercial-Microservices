package com.ecommercial.cart.dto.product;

import com.ecommercial.cart.dto.money.Money;
import lombok.Data;

import java.util.List;
@Data
public class Product {
    private String name;
    private String industrialId;
    private String industrialTypeName;
    private String description;
    private String featuredImageUrl;
    private List<String> imageUrls;
    private Money mediumPrice;
    private Money salePrice;
    private String title;
    private double discount;
    private String tradeMarkId;
    private List<ProductVariant> variants;
}
