/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 9:09 PM
 *  * Last Modified: 12/18/23, 9:09 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.base.mongo;

import java.util.Random;

public class BaseRepository {

    long idCounter = System.currentTimeMillis();

    private String getRandomNumber() {
        int number = new Random().nextInt(999);
        String id = number + "";
        if (number < 10) {
            id = "00" + number;
        } else if (number < 100) {
            id = "0" + number;
        }
        return id;
    }

    public String generateId() {
        idCounter++;
        return idCounter + getRandomNumber();
    }


}
