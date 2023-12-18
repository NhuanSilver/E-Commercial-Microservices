package com.ecommercial.productservice.model.size;

import lombok.Data;

@Data
public class DimensionMeasurement {
    private DimensionUnit dimensionUnit;
    private double width;
    private double height;
    private double length;
}
