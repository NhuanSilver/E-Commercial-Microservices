/*
 * -----------------------------------------------------------------------------
 *  * Copyright (C) 2023 Lê Trung Nhân
 *  * Year of Birth: 22/08/2001
 *  * Nickname: Nero
 *  * Date Created: 12/26/23, 11:07 AM
 *  * Last Modified: 12/26/23, 11:07 AM
 *  * -----------------------------------------------------------------------------
 */

package com.ecommercial.productservice.model.industrial;

import com.ecommercial.productservice.base.model.BaseModel;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(value = "industrialsProduct")
@NoArgsConstructor
@AllArgsConstructor
public class IndustrialProduct extends BaseModel {
    private String name;
    private String iconUrl;
    private String description;
}
