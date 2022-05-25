package com.example.lab2cinema.repo;

import com.example.lab2cinema.model.Ticket;
import com.example.lab2cinema.model.TicketStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class TicketRepoImpl implements TicketRepo{
    @Override
    public List<Ticket> findTicketsBySeanceId(Integer seanceId) {
        return null;
    }

    @Override
    public void generateTickets(int seanceId, int amountOfTickets) {

    }

    @Override
    public List<Ticket> changePriceForAllTickets(int seanceId, int newPrice) {
        return null;
    }

    @Override
    public Ticket changeTicketStatus(Integer seanceId, Integer ticketNumber, TicketStatus ticketStatus) {
        return null;
    }

    @Override
    public Optional<Ticket> findTicketByNumber(Integer seanceId, Integer number) {
        return Optional.empty();
    }
}
