package com.ecommercial.cart.utils;

import com.ecommercial.cart.dto.cart.CartDto;
import com.ecommercial.cart.dto.cart.CartItemDto;
import com.ecommercial.cart.dto.product.ProductVariant;
import com.ecommercial.cart.model.Cart;
import com.ecommercial.cart.model.CartItem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CartMapper {
    private final ModelMapper modelMapper;

    public CartDto toCartDto(Cart cart, List<CartItem> items) {
        CartDto dto = this.modelMapper.map(cart, CartDto.class);
        dto.setItems(items.stream()
                .map(item -> modelMapper.map(item, CartItemDto.class))
                .collect(Collectors.toList()));
        return dto;
    }
}
