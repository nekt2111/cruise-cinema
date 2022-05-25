package com.example.lab2cinema.services;

import com.example.lab2cinema.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findTicketsBySeanceId(Integer seanceId);
    Ticket buyTicket(Integer seanceId,Integer ticketNumber, String email);
    Ticket getTicketByNumber(Integer seanceId, Integer ticketNumber);
    List<Ticket> updatePriceForAllTicketsOnSeance(int seanceId, int newPrice);
    void generateTicketsForSeance(int seanceId, int amountOfTickets);
}
