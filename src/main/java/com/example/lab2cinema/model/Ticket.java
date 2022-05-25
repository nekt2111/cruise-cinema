package com.example.lab2cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private int id;
    private Place place;
    private Seance seance;
    private int price;
    private TicketStatus status;
    private String userEmail;

    public static final int DEFAULT_PRICE = 100;

    public boolean isBought(){
        return this.getStatus() == TicketStatus.Bought;
    }
}
