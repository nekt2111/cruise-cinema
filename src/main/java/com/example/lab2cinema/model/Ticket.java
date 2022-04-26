package com.example.lab2cinema.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ticket {
    private int number;
    private Place place;
    private int seanceId;
    private TicketStatus status;

    public boolean isBought(){
        return this.getStatus() == TicketStatus.Bought;
    }
}
