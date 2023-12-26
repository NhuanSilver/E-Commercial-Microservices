package com.ecommercial.cart.model;

import com.ecommercial.cart.dto.money.Money;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carts")
@Data
@Builder
public class Cart {
    @Id
    private String username;
    private List<Long> itemIds;
    private Money totalPrice;
}
