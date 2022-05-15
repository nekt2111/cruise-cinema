package com.example.lab2cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seance implements Serializable {
    private int id;
   // private String img;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private LocalTime time;

    private final static Comparator<Seance> NAME_COMPARATOR = Comparator.comparing(Seance::getName);
    private final static Comparator<Seance> DATE_COMPARATOR = Comparator.comparing(Seance::getDate);
    private final static Comparator<Seance> TIME_COMPARATOR = Comparator.comparing(Seance::getTime);

    public static Comparator<Seance> getComparatorByName(String name) {

        switch (name) {
            case "name":
                return NAME_COMPARATOR;
            case "time":
                return TIME_COMPARATOR;
            default:
                return DATE_COMPARATOR;
        }
    }

}
