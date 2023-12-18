package com.ecommercial.productservice.base.model;


import com.ecommercial.productservice.enums.CurrencyCode;
import lombok.Data;

@Data
public class MoneyV2 {
    private double amount;
    private CurrencyCode currencyCode;

    public MoneyV2() {
    }

}
