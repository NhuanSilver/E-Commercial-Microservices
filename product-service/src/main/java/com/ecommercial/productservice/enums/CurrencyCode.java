/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/19/23, 9:53 AM
 *  * Last Modified: 12/19/23, 9:53 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.enums;

import com.ecommercial.productservice.enums.utils.BaseEnum;
import lombok.Getter;

@Getter
public enum CurrencyCode {
    VND("Việt Nam đồng"), USD("Đô la Mỹ"),
    EUR("Đồng Euro"), GBP("Bảng Anh");

    private final String description;

    private CurrencyCode(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static String getListName() {
        String listName = "";
        for (CurrencyCode code : values()) {
            listName += code.toString() + ", ";
        }
        return listName;
    }

    public static boolean isExist(Object current) {
        return BaseEnum.isExist(values(), current);
    }
}
