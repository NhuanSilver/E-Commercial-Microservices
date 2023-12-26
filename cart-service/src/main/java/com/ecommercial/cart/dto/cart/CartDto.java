package com.ecommercial.cart.dto.cart;

import com.ecommercial.cart.dto.money.Money;
import lombok.Data;

import java.util.List;

@Data
public class CartDto {
    private String username;
    private List<CartItemDto> items;
    private Money totalPrice;
}
