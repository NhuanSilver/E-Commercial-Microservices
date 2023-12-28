package com.ecommercial.cart.service;

import com.ecommercial.cart.dto.cart.CartItemDto;
import com.ecommercial.cart.dto.input.AddToCartRequest;
import com.ecommercial.cart.dto.cart.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    CartDto getCurrentCart(String username);

    CartDto addToCart(AddToCartRequest request);

    CartItemDto increaseItemQty(Long id);

    CartItemDto decreaseItemQty(Long id);
}
