package com.ecommercial.cart.dto.product;

import lombok.Data;

@Data
public class DimensionMeasurement {
    private DimensionUnit dimensionUnit;
    private double width;
    private double height;
    private double length;
}
