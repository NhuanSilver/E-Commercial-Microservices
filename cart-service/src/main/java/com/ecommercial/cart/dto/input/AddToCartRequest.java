package com.ecommercial.cart.dto.input;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long variantId;
    private int quantity = 1;
    private String username;
}
