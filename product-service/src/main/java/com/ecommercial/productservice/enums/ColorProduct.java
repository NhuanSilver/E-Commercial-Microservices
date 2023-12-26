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
public enum ColorProduct {
    WHITE("Màu trắng"), BLUE("Màu xanh da trời"), GREEN("Màu xanh lá cây"),
    YELLOW("Màu vàng"), PURPLE("Màu tím"), ORANGE("Màu da cam"),
    PINK("Màu hồng"), GRAY("Màu xám"), RED("Màu đỏ"), BLACK("Màu đen"),
    BROWN("Màu nâu"), SILVER("Màu bạc");

    private final String description;

    private ColorProduct(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.name();
    }

    public static String getListName() {
        StringBuilder listName = new StringBuilder();
        for (ColorProduct type : values()) {
            listName.append(type.toString()).append(", ");
        }
        return listName.substring(0, listName.length() - 2);

    }

    public static boolean isExist(Object current) {
        return BaseEnum.isExist(values(), current);
    }

}
