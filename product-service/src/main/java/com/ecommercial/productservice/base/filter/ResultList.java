package com.ecommercial.productservice.base.filter;

import lombok.Data;

import java.util.List;

@Data
public class ResultList<T> {
    private long total;
    private List<T> resultList;
    private long index;
    private long maxResult;

}
