package com.example.lab2cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private int number;
    private Place place;
    private Seance seance;
    private int price;
    private TicketStatus status;
    private String userEmail;

    public boolean isBought(){
        return this.getStatus() == TicketStatus.Bought;
    }
}
