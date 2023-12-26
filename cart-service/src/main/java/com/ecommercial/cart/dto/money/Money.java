package com.ecommercial.cart.dto.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    private String currencyCode;
    private double amount;
}
