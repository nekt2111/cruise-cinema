package com.example.lab2cinema.repo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Filter<T> {

    private SortWay sortWay;
    private String fieldName;
    private String value;

}
