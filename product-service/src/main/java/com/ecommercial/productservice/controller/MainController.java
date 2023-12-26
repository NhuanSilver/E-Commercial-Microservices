/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 10:17 AM
 *  * Last Modified: 12/26/23, 10:17 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.controller;

import java.util.HashMap;

public class MainController {

    protected Object error(String errorCode, String errorMessage, String errorDetail) {
        HashMap<String, String> result = new HashMap<>();
        result.put("errorCode", errorCode);
        result.put("errorMessage", errorMessage);
        result.put("errorDetail", errorDetail);
        return result;
    }

    protected Object success(Object data) {
        return data;
    }
}
