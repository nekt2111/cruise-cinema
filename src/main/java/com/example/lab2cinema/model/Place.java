package com.example.lab2cinema.model;

import com.example.lab2cinema.repo.Ref;
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

    public static Place fromNumber(int number, int maxColumn) {
        var row = new Ref<Integer>();
        var column = new Ref<Integer>();

        getRowAndColumn(number, maxColumn, row, column);

        return new Place(number, row.Value, column.Value);
    }

    public static void getRowAndColumn(int seat, int maxColumn, Ref<Integer> outRow, Ref<Integer> outColumn) {

        outColumn.Value = (seat - 1) % maxColumn + 1;
        outRow.Value = (seat - 1) / maxColumn + 1;
    }

}
