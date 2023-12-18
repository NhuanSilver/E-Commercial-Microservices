package com.ecommercial.productservice.enums;

import com.ecommercial.productservice.enums.utils.BaseEnum;

public enum CurrencyCode {
    VND("Việt Nam đồng"), USD("Đô la Mỹ"),
    EUR("Đồng Euro"), GBP("Bảng Anh");

    private final String description;

    private CurrencyCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
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
