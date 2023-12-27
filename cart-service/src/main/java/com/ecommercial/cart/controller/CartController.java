package com.ecommercial.cart.controller;

import com.ecommercial.cart.dto.cart.CartItemDto;
import com.ecommercial.cart.dto.input.AddToCartRequest;
import com.ecommercial.cart.dto.cart.CartDto;
import com.ecommercial.cart.model.CartItem;
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
    @PutMapping("/item/{id}/increase")
    public CartItemDto increaseItemQty(@PathVariable Long id) {
        return this.cartService.increaseItemQty(id);
    }
    @PutMapping("/item/{id}/decrease")
    public CartItemDto decreaseItemQty(@PathVariable Long id) {
        return this.cartService.decreaseItemQty(id);
    }
}
