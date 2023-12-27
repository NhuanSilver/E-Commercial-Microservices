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

import com.ecommercial.productservice.base.filter.BaseFilter;
import com.ecommercial.productservice.base.filter.ResultList;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
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

    protected void betweenFilter(double from, double to, List<Bson> filter) {
        if (from > 0 && to > 0) {
            filter.add(Filters.and(Filters.gte("mediumPrice.amount", from), Filters.lte("mediumPrice.amount", to)));
        } else if (from > 0) {
            filter.add(Filters.gte("mediumPrice.amount", from));
        } else if (to > 0) {
            filter.add(Filters.lte("mediumPrice.amount", to));
        }
    }

    protected void appendFilter(Object value, String key, List<Bson> filter) {
        if (null != value) {
            if (String.valueOf(value).trim().length() > 0) {
                filter.add(Filters.eq(key, value));
            }
        }
    }
    protected ResultList getResultList(MongoCollection collection, List<Bson> filter, int offset, int maxResult) {
        Bson filters = Filters.and(filter);
        long countDoc = collection.countDocuments();
        FindIterable itr = collection.find();
        if (filter.size() > 0) {
            itr = collection.find(filters);
            countDoc = collection.countDocuments(filters);
        }
        ResultList resultList = new ResultList();
        resultList.setResultList(new ArrayList());
        if (countDoc > 0) {
            boolean hasFilterText = false;
            for (Bson bson : filter) {
                String f = bson.toString();
                if (null != f && f.startsWith("Text Filter")) {
                    hasFilterText = true;
                }
            }
            if (maxResult > 0) {
                itr.limit(maxResult);
                itr.skip(offset);
            }
            if (hasFilterText) {
                itr = itr.projection(Projections.metaTextScore("score"))
                        .sort(Sorts.metaTextScore("score"));
            } else {
                itr = itr.sort(Sorts.descending("createdAt"));
            }
            List list = (List) itr.into(new ArrayList<>());
            resultList.setResultList(list);
            resultList.setMaxResult(maxResult);
            resultList.setIndex(offset);
            resultList.setTotal(countDoc);
        }
        return resultList;
    }

    protected List<Bson> getFilters(BaseFilter baseFilter) {
        return baseFilter.getFilterList();
    }

}
