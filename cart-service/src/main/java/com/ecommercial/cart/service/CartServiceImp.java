package com.ecommercial.cart.service;

import com.ecommercial.cart.dto.cart.CartItemDto;
import com.ecommercial.cart.dto.money.Money;
import com.ecommercial.cart.dto.input.AddToCartRequest;
import com.ecommercial.cart.dto.cart.CartDto;
import com.ecommercial.cart.dto.product.Product;
import com.ecommercial.cart.dto.product.ProductVariant;
import com.ecommercial.cart.exception.NotFoundException;
import com.ecommercial.cart.model.Cart;
import com.ecommercial.cart.model.CartItem;
import com.ecommercial.cart.proxy.ProductProxy;
import com.ecommercial.cart.repository.CartItemRepository;
import com.ecommercial.cart.repository.CartRepository;
import com.ecommercial.cart.utils.CartItemMapper;
import com.ecommercial.cart.utils.CartMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CartServiceImp implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository itemRepository;
    private final CartMapper cartMapper;
    private final CartItemMapper itemMapper;
    private final ProductProxy productProxy;

    @Override
    public CartDto getCurrentCart(String username) {

        Cart cart = this.cartRepository.getCartByUsername(username)
                .orElseThrow(() -> new NotFoundException("Cart not found by this username " + username));
        List<CartItem> items = cart.getItemIds().stream()
                .map(id -> itemRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("item not found")))
                .collect(Collectors.toList());
        return cartMapper.toCartDto(cart, items);
    }

    @Override
    @Transactional
    public CartDto addToCart(AddToCartRequest request) {

        ProductVariant variant = productProxy.getProductVariantById(request.getVariantId());

        Product product = productProxy.getProductDetail(String.valueOf(variant.getProductId()));

        // Validate product
        if (product == null) throw new NotFoundException("product not found");


        CartItem item;
        Cart cart = cartRepository.getCartByUsername(request.getUsername()).orElse(null);

        // Cart existed
        if (cart != null) {
            item = itemRepository.findById(variant.getId()).orElse(null);

            // Null Cart Item
            if (item == null) {
                item = CartItem.builder()
                        .variantId(request.getVariantId())
                        .quantity(request.getQuantity())
                        .cartId(cart.getUsername())
                        .build();
                cart.getItemIds().add(item.getVariantId());
            }

            item.setQuantity(item.getQuantity() + request.getQuantity());

        } else {

            // Create new Cart
            item = CartItem.builder()
                    .variantId(variant.getId())
                    .quantity(request.getQuantity())
                    .cartId(request.getUsername())
                    .build();

            cart = Cart.builder()
                    .itemIds(List.of(item.getVariantId()))
                    .username(request.getUsername())
                    .build();

        }
        itemRepository.save(item);
        List<CartItem> items = new ArrayList<>();

        for (Long id : cart.getItemIds()) {
            CartItem exItem = itemRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("item not found"));
            items.add(exItem);
        }

        double amount;
        double newAmount = request.getQuantity() * variant.getSalePrice().getAmount();

        if (cart.getTotalPrice() != null) {
            amount = cart.getTotalPrice().getAmount() + newAmount;
        } else {
            amount = newAmount;
        }

        Money money = Money.builder()
                .amount(amount)
                .currencyCode("VND")
                .build();
        cart.setTotalPrice(money);

        return cartMapper.toCartDto(cartRepository.save(cart), items);
    }

    @Override
    public CartItemDto increaseItemQty(Long id) {
        CartItem item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
        item.setQuantity(item.getQuantity() + 1);
        return itemMapper.toCartItemDto(itemRepository.save(item));
    }

    @Override
    public CartItemDto decreaseItemQty(Long id) {
        CartItem item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found"));
        if (item.getQuantity() > 1) item.setQuantity(item.getQuantity() - 1);
        return itemMapper.toCartItemDto(itemRepository.save(item));
    }
}
