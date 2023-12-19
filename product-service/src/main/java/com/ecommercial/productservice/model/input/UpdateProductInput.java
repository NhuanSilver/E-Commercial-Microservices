/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/18/23, 8:36 PM
 *  * Last Modified: 12/18/23, 8:36 PM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.input;

import java.util.List;

public class UpdateProductInput {
    private String name;
    private String industrialId;
    private String industrialTypeName;
    private String description;
    private String featuredImageUrl;
    private List<String> imageUrls;
    private double mediumPrice;
    private String title;
    private String tradeMarkId;
}
