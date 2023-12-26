package com.ecommercial.cart.controller;

import com.ecommercial.cart.dto.input.AddToCartRequest;
import com.ecommercial.cart.dto.cart.CartDto;
import com.ecommercial.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final ICartService cartService;

    @GetMapping("/{username}")
    public CartDto getCurrentCart(@PathVariable String username) {
        return this.cartService.getCurrentCart(username);
    }
    @PutMapping
    public CartDto addToCart(@RequestBody AddToCartRequest request) {
        return this.cartService.addToCart(request);
    }
}
