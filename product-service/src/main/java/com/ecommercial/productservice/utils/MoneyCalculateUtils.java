/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 12:55 PM
 *  * Last Modified: 12/26/23, 12:55 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.utils;


import com.ecommercial.productservice.base.model.MoneyV2;
import com.ecommercial.productservice.enums.CurrencyCode;

public class MoneyCalculateUtils {

    public static MoneyV2 cloneMoney(MoneyV2 cloneValue, double amount) {
        if (null == cloneValue) {
            cloneValue = new MoneyV2();
            cloneValue.setCurrencyCode(CurrencyCode.VND);
        }
        MoneyV2 moneyV2 = new MoneyV2();
        moneyV2.setCurrencyCode(cloneValue.getCurrencyCode());
        moneyV2.setAmount(amount);
        return moneyV2;
    }

    public static MoneyV2 getMoney(double value) {
        MoneyV2 moneyV2 = new MoneyV2();
        moneyV2.setCurrencyCode(CurrencyCode.VND);
        moneyV2.setAmount(value);
        return moneyV2;
    }

    public static double getMoneyAmount(MoneyV2 value) {
        if (null != value) {
            return value.getAmount();
        }
        return 0;
    }

    public static MoneyV2 calculateDiscount(double price, double discount) {
        double salePrice = price - (price * discount / 100);
        return getMoney(salePrice);
    }
}
