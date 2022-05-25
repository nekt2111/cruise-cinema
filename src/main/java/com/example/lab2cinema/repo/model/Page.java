package com.example.lab2cinema.repo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private Integer pageNumber;
    private Integer pageSize;

    private static final Integer DEFAULT_PAGE_SIZE = 3;

    public static Page createFromStrings(String offSet, String pageSize){
        return new Page(Integer.parseInt(pageSize),Integer.parseInt(offSet));
    }

    public static Page createFromStrings(String pageNumber){
        return new Page(Integer.parseInt(pageNumber),DEFAULT_PAGE_SIZE);
    }
}
