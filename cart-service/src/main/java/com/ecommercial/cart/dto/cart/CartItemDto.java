package com.ecommercial.cart.dto.cart;

import lombok.Builder;
import lombok.Data;

@Data
public class CartItemDto {
    private Long variantId;
    private Integer quantity;
}
