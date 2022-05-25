package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Seance;
import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;

import java.util.List;
import java.util.Optional;

public interface TicketRepo {

    List<Ticket> findTicketsBySeanceId(Integer seanceId);
    void generateTickets(int seanceId, int amountOfTickets);
    List<Ticket> changePriceForAllTickets(int seanceId, int newPrice);
    Ticket changeTicketStatus(Integer seanceId, Integer ticketNumber, TicketStatus ticketStatus);

    void updateTicketBuyerEmail(Integer seanceId, Integer ticketNumber,String email);
    Optional<Ticket> findTicketByNumber(Integer seanceId, Integer number);

}
