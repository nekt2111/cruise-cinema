package com.example.lab2cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Place {
    private int number;
    private int row;
    private int column;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return number == place.number && row == place.row && column == place.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, row, column);
    }
}
