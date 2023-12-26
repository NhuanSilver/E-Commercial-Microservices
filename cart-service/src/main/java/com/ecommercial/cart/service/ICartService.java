package com.ecommercial.cart.service;

import com.ecommercial.cart.dto.input.AddToCartRequest;
import com.ecommercial.cart.dto.cart.CartDto;
import com.ecommercial.cart.model.Cart;
import org.springframework.stereotype.Service;

@Service
public interface ICartService {
    CartDto getCurrentCart(String username);

    CartDto addToCart(AddToCartRequest request);
}
