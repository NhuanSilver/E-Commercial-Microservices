package com.ecommercial.cart.utils;

import com.ecommercial.cart.dto.cart.CartItemDto;
import com.ecommercial.cart.model.CartItem;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CartItemMapper {
    private final ModelMapper modelMapper;

    public CartItemDto toCartItemDto(CartItem item){
        CartItemDto dto = this.modelMapper.map(item, CartItemDto.class);
        return dto;
    }
}
