/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:51 AM
 *  * Last Modified: 12/26/23, 11:51 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.utils;

public class GeneralIdUtils {
    public static String generateId() {
        return String.valueOf(
                (int) Math.floor(
                        ((Math.random() * 899999) + 100000)));// 6 digits
    }

}
