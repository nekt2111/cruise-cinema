package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findTicketsBySeanceId(Integer seanceId);
    Ticket buyTicket(Integer seanceId,Integer ticketNumber);
    Ticket getTicketByNumber(Integer seanceId, Integer ticketNumber);
    void updatePriceForAllTicketsOnSeance(int seanceId, int newPrice);
    void generateTicketsForSeance(int seanceId, int amountOfTickets);
}
